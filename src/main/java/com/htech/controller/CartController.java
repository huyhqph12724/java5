package com.htech.controller;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.htech.model.Cart;
import com.htech.model.Order;
import com.htech.model.OrderDetail;
import com.htech.model.Product;
import com.htech.model.Users;
import com.htech.service.OrderDetailService;
import com.htech.service.OrderService;
import com.htech.service.ProductService;
import com.htech.service.UserService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@GetMapping("")
	public String card() {
		return "website/cart";
	}
	@RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
    public String viewAdd(ModelMap mm, @PathVariable("productId") int productId) {
        @SuppressWarnings("unchecked")
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        Optional<Product> opt = productService.findById(productId);
        if (opt.isPresent()) {
        	Product product = opt.get();
            if (cartItems.containsKey(productId)) {
                Cart item = cartItems.get(productId);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);
                cartItems.put(productId, item);
            } else {
                Cart item = new Cart();
                item.setProduct(product);
                item.setQuantity(1);
                cartItems.put(productId, item);
            }
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "website/cart";
    }

//    @RequestMapping(value = "sub/{productId}", method = RequestMethod.GET)
//    public String viewUpdate(ModelMap mm, @PathVariable("productId") int productId) {
//        @SuppressWarnings("unchecked")
//		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
//        if (cartItems == null) {
//            cartItems = new HashMap<>();
//        }
//        session.setAttribute("myCartItems", cartItems);
//        return "website/cart";
//    }

    @RequestMapping(value = "remove/{productId}", method = RequestMethod.GET)
    public String viewRemove(ModelMap mm, @PathVariable("productId") int productId) {
        @SuppressWarnings("unchecked")
		HashMap<Integer, Cart> cartItems =  (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(productId)) {
            cartItems.remove(productId);
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("myCartTotal", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "website/cart";
    }

    public double totalPrice(HashMap<Integer, Cart> cartItems) {
        int count = 0;
        for (Entry<Integer, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
        }
        return count;
    }
    @GetMapping(value = "checkout")
    public String viewCheckout(ModelMap mm) {
            @SuppressWarnings("unchecked")
			Map<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
            Optional<Users> opt = userService.findById((String) session.getAttribute("username"));
            Users user = opt.get();
            Order order = new Order();
            if (cartItems == null) {
                cartItems = new HashMap<>();
            }
            
            order.setCreatedate(new Date());
            order.setUser(user);
            order.setStatus(false);
            orderService.save(order);
//            for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
//            	OrderDetail item = new OrderDetail();
//                item.setOrder(order);
//                item.setProduct(entry.getValue().getProduct());
//                item.setPrice(entry.getValue().getProduct().getPrice());
//                item.setQuantity(entry.getValue().getQuantity());
//   
//                orderDetailService.save(item);
//            }
            List<Cart> values = cartItems.values().stream().collect(Collectors.toList());
            
            	OrderDetail item = new OrderDetail();
            	item.setOrder(order);
            	item.setProduct(values.get(0).getProduct());
            	item.setPrice(values.get(0).getProduct().getPrice());
            	item.setQuantity(values.get(0).getQuantity());
            	orderDetailService.save(item);
           
            cartItems = new HashMap<>();
            session.setAttribute("myCartItems", cartItems);
            session.setAttribute("myCartTotal", 0);
            session.setAttribute("myCartNum", 0);
            return "redirect:/home";
    }

}

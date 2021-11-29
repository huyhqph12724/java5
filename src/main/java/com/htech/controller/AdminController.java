package com.htech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htech.model.Category;
import com.htech.model.Order;
import com.htech.model.Product;
import com.htech.model.Users;
import com.htech.service.CategoryService;
import com.htech.service.OrderService;
import com.htech.service.ProductService;
import com.htech.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	OrderService orderService;
	@GetMapping("")
	public String admin() {
		
		if(request.getSession().getAttribute("admin")==null) {
			return "redirect:/login";
		}
		return "admin/indexad";
	}
	@GetMapping("category")
	public String category(@ModelAttribute("category") Category category,Model model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("list",list);
		return "/admin/category";
	}
	@GetMapping("user")
	public String user(@ModelAttribute("user") Users user,Model model) {
		List<Users> list = userService.findAll();
		model.addAttribute("list",list);
		return "/admin/user";
	}
	@GetMapping("product")
	public String product(@ModelAttribute("product") Product product,Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("list",list);
		return "/admin/product"; 
	}
	@GetMapping("order")
	public String product(Model model) {
		List<Order> list = orderService.findAll();
		model.addAttribute("list",list);
		return "/admin/order"; 
	}
}

package com.htech.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.htech.model.Product;
import com.htech.service.CategoryService;
import com.htech.service.ProductService;
import com.htech.service.UserService;

@Controller
@RequestMapping("admin/product")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ServletContext app;
	@GetMapping("delete/pid={pid}")
	public String delete(@PathVariable("pid") String id) {
		System.out.println("ID là...."+id);
		Optional<Product> opt = productService.findById(Integer.parseInt(id));
		if (opt.isPresent()) {
			Product p = opt.get();
			productService.delete(p);
		}
		return "redirect:/admin/product";
	}
	@GetMapping("add")
	public String add(@ModelAttribute("product") Product product){
		return "/admin/addproduct";
	}
	@PostMapping("add")
	public String save(@Valid @ModelAttribute("product") Product product, BindingResult result,
			@RequestParam("img") MultipartFile imgs, Model model) {
		if (result.hasErrors()) {
			System.out.println("có lỗi");
			return "admin/addproduct";
		} else {
				try {
					if (!imgs.isEmpty()) {
							Date now = new Date();
							String filename = imgs.getOriginalFilename();
							File file = new File(app.getRealPath("/img/") + filename);
							imgs.transferTo(file);
							product.setImage(filename);
							product.setCreatedate(now);
							product.setAvailable(true);
							productService.save(product);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return "redirect:/admin/product";
	}
	@GetMapping("update/pid={pid}")
	public String edit(@PathVariable("pid") Integer id, Model model) {
		System.out.println(id);
		Optional<Product> opt = productService.findById(id);
		Product product = opt.get();
		model.addAttribute("product", product);
		return "admin/editproduct";
	}
	@PostMapping("update")
	public String editUser(@Valid @ModelAttribute("product") Product p, BindingResult result, Model model,@RequestParam("img") MultipartFile imgs) {
		if(result.hasErrors()) {
			System.out.println("có lỗi");
			return "admin/editproduct";
		}
		if (!imgs.isEmpty()) {
			try {
				String filename = imgs.getOriginalFilename();
				File file = new File(app.getRealPath("/img/") + filename);
				imgs.transferTo(file);
				p.setImage(filename);
				System.out.println(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productService.save(p);
		return "redirect:/admin/product";
	}
	@ModelAttribute("category")
	public Map<Integer, String> getHobbies(){
	Map<Integer, String> map = new HashMap<>();
	for(com.htech.model.Category x:categoryService.findAll()) {
		map.put(x.getId(), x.getName());
	}
	return map;
	}
	@ModelAttribute("status")
	public Map<Boolean, String> getGenderList() {
		Map<Boolean, String> rolelist = new HashMap<Boolean, String>();
		rolelist.put(true, "Còn hàng");
		rolelist.put(false, "Hết hàng");
		return rolelist;

	}
}

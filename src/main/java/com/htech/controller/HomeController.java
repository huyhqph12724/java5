package com.htech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.htech.model.Category;
import com.htech.model.Product;
import com.htech.service.CategoryService;
import com.htech.service.ProductService;

import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;


@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@GetMapping("/home")
	public String home(Model model) {
		return "redirect:/home/page?p=0";
		
	}
	@GetMapping("home/page")
	public String pageable(@RequestParam("p") int p,Model model) {
		int sotrang;
		int count = (int) productService.count();
		if(count%3==0) {
			sotrang = count/3;
		}else sotrang = count/3+1;
		System.out.println("số trang"+sotrang);
		model.addAttribute("sotrang",sotrang);
		Page<Product> page = productService.findAll(PageRequest.of(p, 3));
		model.addAttribute("page", page);
		return "/website/index";
	}
	
}

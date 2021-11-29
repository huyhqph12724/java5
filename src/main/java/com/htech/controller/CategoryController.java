package com.htech.controller;

import java.util.Optional;

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
import com.htech.model.Category;
import com.htech.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("delete/cid={cid}")
	public String delete(@PathVariable("cid") String id, Model model) {
		try {
			System.out.println("id là:" +id);
			categoryService.deleteById(Integer.parseInt(id));
			model.addAttribute("mss","Xóa thành công");
		} catch (Exception e) {
			System.out.println("Xóa thất bại");
		}
		return "redirect:/admin/category";
	}
	@PostMapping("/add")
	public String addCategory(@Valid @ModelAttribute("category") Category category,BindingResult result, Model model) {
		int i=0;
		System.out.println(category.getName());
		if(result.hasErrors()) {
			System.out.println("có lỗi");
			return "admin/category";
		}else {
			
			for (Category c : categoryService.findAll()) {
				if (c.getName().equalsIgnoreCase(category.getName())) {
					i++;
				}
			}
			if(i==0) {
				category.setName(category.getName());
				categoryService.save(category);
			}
			else System.out.println("Đã tồn tại");
		}
		return "redirect:/admin/category";
	}
	@GetMapping("edit/cid={cid}")
	public String edit(@PathVariable("cid") Integer id, Model model) {
		System.out.println(id);
		Optional<Category> opt = categoryService.findById(id);
		Category category = opt.get();
		model.addAttribute("category",category);
		return "admin/editcategory";
	}
	@PostMapping("edit")
	public String update(@Valid @ModelAttribute("category") Category category,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "admin/editcategory";
		}
		else {
			categoryService.save(category);
			return "redirect:/admin/category";
		}
	}
}

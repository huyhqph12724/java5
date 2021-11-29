package com.htech.controller;

import java.io.File;
import java.util.HashMap;
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

import com.htech.model.Category;
import com.htech.model.Users;
import com.htech.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class CustomerController {
	@Autowired
	UserService userService;
	@Autowired
	ServletContext app;
	@GetMapping("add")
	public String add(@ModelAttribute("user") Users user) {
		return "admin/adduser";
	}
	@PostMapping("add")
	public String save(@Valid @ModelAttribute("user") Users user, BindingResult result,
			@RequestParam("img") MultipartFile imgs, Model model) {
		if (result.hasErrors()) {
			System.out.println("có lỗi");
			return "admin/adduser";
		} else {
			Optional<Users> u = userService.findById(user.getUsername());
			if (u.isPresent()) {
				model.addAttribute("mss", "Người dùng đã tồn tại");
				return "/admin/adduser";
			} else {
				try {
					if (!imgs.isEmpty()) {
							String filename = imgs.getOriginalFilename();
							File file = new File(app.getRealPath("/img/") + filename);
							imgs.transferTo(file);
							user.setPhoto(filename);	
							System.out.println(filename);
							userService.save(user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/admin/user";
	}

	@GetMapping("delete/uid={uid}")
	public String delete(@PathVariable("uid") String id) {

		Optional<Users> opt = userService.findById(id);
		if (opt.isPresent()) {
			Users u = opt.get();
			userService.delete(u);
		}
		return "redirect:/admin/user";
	}
	@GetMapping("update/uid={uid}")
	public String editUser(@PathVariable("uid") String id, Model model) {
		Optional<Users> opt = userService.findById(id);
		if(opt.isPresent()) {
			Users u = opt.get();
			model.addAttribute("user",u);
		}
		return "admin/edituser";
	}
	@PostMapping("update")
	public String editUser(@Valid @ModelAttribute("user") Users u, BindingResult result, Model model,@RequestParam("img") MultipartFile imgs) {
		if(result.hasErrors()) {
			return "admin/edituser";
		}
		if (!imgs.isEmpty()) {
			try {
				String filename = imgs.getOriginalFilename();
				File file = new File(app.getRealPath("/img/") + filename);
				imgs.transferTo(file);
				u.setPhoto(filename);
				System.out.println(filename);
				userService.save(u);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return "redirect:/admin/user";
	}
	@ModelAttribute("role")
	public Map<Boolean, String> getGenderList() {
		Map<Boolean, String> rolelist = new HashMap<Boolean, String>();
		rolelist.put(true, "Admin");
		rolelist.put(false, "User");
		return rolelist;

	}

	@ModelAttribute("active")
	public Map<Boolean, String> active() {
		Map<Boolean, String> active = new HashMap<Boolean, String>();
		active.put(false, "Inactive");
		active.put(true, "Active");
		return active;

	}
}

package com.htech.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.htech.model.Users;
import com.htech.service.CookieService;
import com.htech.service.UserService;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;


@Controller
public class LoginController {
	@Autowired
	ServletContext app;
	@Autowired
	CookieService cookieService;
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
    JavaMailSender mailSender;
	@GetMapping("/login")
	public String login(Model model) {
		Cookie[] ck = request.getCookies();
		if (ck != null) {
			for (Cookie x : ck) {
				if (x.getName().equals("username")) {
					model.addAttribute("username", x.getValue());
				}
				if (x.getName().equals("pass")) {
					model.addAttribute("pass", x.getValue());
				}
			}
		}
		return ("admin/login");
	}
	@PostMapping("/login")
	public String login(@RequestParam("username") String username,@RequestParam("pass") String pass,Object remember,Model model) {
		java.util.Optional<Users> opt = userService.findById(username);
		model.addAttribute("username",username);
		if(opt.isEmpty()) {
			model.addAttribute("u","Tên đăng nhập sai");
		}else {
			Users u = opt.get();
			if(!u.getPassword().equals(pass)) {
				model.addAttribute("p","Mật khẩu sai");
			}else {
				session.setAttribute("username", u.getUsername());
				System.out.println("Session: "+request.getSession().getAttribute("username"));
				session.setAttribute("isLogin",true);
				if(u.getAdmin()) session.setAttribute("admin",true);
				if(!u.getAdmin()) session.removeAttribute("admin");
				if(remember!=null) {
					cookieService.add("username",u.getUsername(), 24);
					cookieService.add("pass",u.getPassword(), 24);
					System.out.println("Cookie:"+ cookieService.getValue("username"));
					System.out.println("is login"+ request.getSession().getAttribute("isLogin"));
				}
				return "redirect:/home";
			}
		}
		return ("/admin/login");
	}
	@GetMapping("/logout")
	public String logout(Model model) {
		session.removeAttribute("username");
		session.setAttribute("isLogin",false);
		System.out.println("login:"+request.getSession().getAttribute("isLogin"));
		return "redirect:/home";
	}
	@GetMapping("/register")
	public String register(@ModelAttribute("user") Users user) {
		return ("admin/register");
	}
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") Users user,BindingResult result,Model model,
			MultipartFile imgs,@RequestParam("username") String username,@RequestParam("email") String email) {
		int i = 0;
		if(result.hasErrors()) {
			return "/admin/register";
		}
		if(username!=null&&username!="") {
			java.util.Optional<Users> opt = userService.findById(username);
			if(opt.isPresent()) {
				model.addAttribute("mss","tên đăng nhập đã tồn tại");
				return "/admin/register";
			}
			for(Users u:userService.findAll()) {
				if(u.getEmail().equals(email)) i++;
			}
			if(i>0) {
				model.addAttribute("emailerr","Email đã tồn tại");
				return "/admin/register";
			}
			try {
				if (!imgs.isEmpty()) {
						String filename = imgs.getOriginalFilename();
						File file = new File(app.getRealPath("/img/") + filename);
						imgs.transferTo(file);
						user.setPhoto(filename);
						user.setActive(true);
						System.out.println(filename);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			userService.save(user);
		}
		return ("admin/login");
	}
	@GetMapping("/forgotpassword")
	public String forgotpassword() {
		return ("admin/forgot-password");
	}
	@PostMapping("/forgotpassword")
	public String forgotpassword(@RequestParam("email") String email,Model model) {
		if(email!=null) {
			for(Users user :userService.findAll()) {
				if(!user.getEmail().equals(email)) {
					model.addAttribute("mss","email không tồn tại");
					return "/admin/forgot-password";
				}
				if(user.getEmail().equals(email)) {
					SimpleMailMessage message = new SimpleMailMessage();
			        
			        message.setTo(email);
			        message.setSubject("From Htech Shop");
			        message.setText("Your password is: "+ user.getPassword());
			        mailSender.send(message);
			        break;
				}
			}
		}
		return "redirect:/login";
	}
	@GetMapping("upfile")
	public String upfile() {
		
		return ("uploadimg");
	}

	@PostMapping("upfile")
	public String getimg(@RequestParam("img") MultipartFile[] imgs, Model model) throws IOException {
		try {
			if (imgs.length>0) {
				for(MultipartFile x:imgs) {
					String filename = x.getOriginalFilename();
					File file = new File(app.getRealPath("/img/") + filename);
					x.transferTo(file);
					System.out.println(filename);
				}	
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ("uploadimg");
	}
}

package com.htech.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htech.service.OrderService;
import com.htech.model.Order;
@Controller
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	OrderService orderService;
	@GetMapping("order/update/oid={oid}")
	public String delete(@PathVariable("oid") Integer id) {

		Optional<Order> opt = orderService.findById(id);
		if (opt.isPresent()) {
			Order o = opt.get();
			o.setStatus(true);
			orderService.save(o);
		}
		return "redirect:/admin/order";
	}
}

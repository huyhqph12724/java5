package com.htech.service;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.htech.dao.CategoryRepo;
import com.htech.dao.OrderRepo;
import com.htech.model.Order;
import com.htech.model.OrderDetail;
@Service
@SessionScope

public class OrderServerImp implements OrderService{
	OrderRepo orderepo ;
	public OrderServerImp(OrderRepo orderepo) {
		this.orderepo = orderepo;
	}
	
	@Override
	public <S extends Order> S save(S entity) {
		return orderepo.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return orderepo.findAll();
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return orderepo.findById(id);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		return orderepo.saveAll(entities);
	}

	@Override
	public Order getById(Integer id) {
		return orderepo.getById(id);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

}

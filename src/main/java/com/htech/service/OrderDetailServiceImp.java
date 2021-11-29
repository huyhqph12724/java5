package com.htech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.htech.dao.OrderdetailRepo;
import com.htech.model.Order;
import com.htech.model.OrderDetail;
@Service
public class OrderDetailServiceImp implements OrderDetailService{
	OrderdetailRepo orderDetailRepo;

	public OrderDetailServiceImp(OrderdetailRepo orderDetailRepo) {
		this.orderDetailRepo = orderDetailRepo;
	}

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepo.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepo.findAll();
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return orderDetailRepo.findAllById(ids);
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return orderDetailRepo.findById(id);
	}

	@Override
	public long count() {
		return orderDetailRepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderDetailRepo.deleteById(id);
	}

	@Override
	public OrderDetail getById(Integer id) {
		return orderDetailRepo.getById(id);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

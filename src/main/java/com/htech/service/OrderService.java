package com.htech.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.htech.model.Order;
import com.htech.model.OrderDetail;

public interface OrderService {

	List<OrderDetail> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	Order getById(Integer id);

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	Optional<Order> findById(Integer id);

	List<Order> findAll();

	<S extends Order> S save(S entity);

	
}

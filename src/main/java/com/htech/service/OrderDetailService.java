package com.htech.service;

import java.util.List;
import java.util.Optional;

import com.htech.model.Order;
import com.htech.model.OrderDetail;

public interface OrderDetailService {

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	OrderDetail getById(Integer id);

	void deleteById(Integer id);

	long count();

	Optional<OrderDetail> findById(Integer id);

	List<OrderDetail> findAllById(Iterable<Integer> ids);

	List<OrderDetail> findAll();

	<S extends OrderDetail> S save(S entity);

}

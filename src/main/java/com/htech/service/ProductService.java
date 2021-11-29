package com.htech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.htech.model.Product;

public interface ProductService {

	Product getById(Integer id);

	void delete(Product entity);

	void deleteById(Integer id);

	<S extends Product> S saveAndFlush(S entity);

	void flush();

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	Page<Product> findAll(Pageable pageable);

	long count();

}

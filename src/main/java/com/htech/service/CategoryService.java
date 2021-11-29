package com.htech.service;

import java.util.List;
import java.util.Optional;

import com.htech.model.Category;

public interface CategoryService {

	Category getById(Integer id);

	void deleteById(Integer id);

	void flush();

	Optional<Category> findById(Integer id);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	<S extends Category> S saveAndFlush(S entity);
	
}

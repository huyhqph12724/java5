package com.htech.service;

import java.util.List;

import com.htech.dao.CategoryRepo;
import com.htech.model.Category;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public class CategoryServiceImp implements CategoryService {
	CategoryRepo categoryrepo ;

	public CategoryServiceImp(CategoryRepo categoryrepo) {
		this.categoryrepo = categoryrepo;
	}

	@Override
	public <S extends Category> S save(S entity) {
		return categoryrepo.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return categoryrepo.findAll();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryrepo.findById(id);
	}

	@Override
	public void flush() {
		categoryrepo.flush();
	}

	@Override
	public void deleteById(Integer id) {
		categoryrepo.deleteById(id);
	}

	@Override
	public Category getById(Integer id) {
		return categoryrepo.getById(id);
	}
	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryrepo.saveAndFlush(entity);
	}
	
	
}

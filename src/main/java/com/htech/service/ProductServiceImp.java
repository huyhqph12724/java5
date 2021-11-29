package com.htech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.htech.dao.ProductRepo;
import com.htech.model.Product;

@Service
public class ProductServiceImp implements ProductService{
	ProductRepo productRepo;

	public ProductServiceImp(ProductRepo productRepo) {
		
		this.productRepo = productRepo;
	}

	@Override
	public long count() {
		return productRepo.count();
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepo.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);
	}

	@Override
	public void flush() {
		productRepo.flush();
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return productRepo.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Integer id) {
		productRepo.deleteById(id);
	}

	@Override
	public void delete(Product entity) {
		productRepo.delete(entity);
	}

	@Override
	public Product getById(Integer id) {
		return productRepo.getById(id);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepo.findAll(pageable);
	}
	
	
	
}

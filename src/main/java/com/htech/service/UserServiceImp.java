package com.htech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.htech.dao.UserRepo;
import com.htech.model.Users;

@Service
public class UserServiceImp implements UserService{
	UserRepo userRepo;

	public UserServiceImp(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public <S extends Users> S save(S entity) {
		return userRepo.save(entity);
	}

	@Override
	public List<Users> findAll() {
		return userRepo.findAll();
	}

	@Override
	public Optional<Users> findById(String id) {
		return userRepo.findById(id);
	}

	@Override
	public void flush() {
		userRepo.flush();
	}

	@Override
	public <S extends Users> S saveAndFlush(S entity) {
		return userRepo.saveAndFlush(entity);
	}

	@Override
	public long count() {
		return userRepo.count();
	}

	@Override
	public void deleteById(String id) {
		userRepo.deleteById(id);
	}

	@Override
	public void delete(Users entity) {
		userRepo.delete(entity);
	}

	@Override
	public Users getById(String id) {
		return userRepo.getById(id);
	}

	
	
}

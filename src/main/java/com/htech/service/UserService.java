package com.htech.service;

import java.util.List;
import java.util.Optional;

import com.htech.model.Users;

public interface UserService {

	Users getById(String id);

	void delete(Users entity);

	void deleteById(String id);

	long count();

	<S extends Users> S saveAndFlush(S entity);

	void flush();

	Optional<Users> findById(String id);

	List<Users> findAll();

	<S extends Users> S save(S entity);

	

}

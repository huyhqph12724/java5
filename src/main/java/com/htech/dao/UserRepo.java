package com.htech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htech.model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users,String> {

}

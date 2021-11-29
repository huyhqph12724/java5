package com.htech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htech.model.OrderDetail;
@Repository
public interface OrderdetailRepo extends JpaRepository<OrderDetail,Integer>{

}

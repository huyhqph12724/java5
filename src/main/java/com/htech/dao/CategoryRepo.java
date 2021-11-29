package com.htech.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.htech.model.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
	
}

package com.idat.idatapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.idatapirest.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

	
}

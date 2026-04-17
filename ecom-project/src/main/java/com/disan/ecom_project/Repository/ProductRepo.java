package com.disan.ecom_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disan.ecom_project.Model.Product;



@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{

}

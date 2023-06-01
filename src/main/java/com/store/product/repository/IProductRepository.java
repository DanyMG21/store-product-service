package com.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.product.entity.Category;
import com.store.product.entity.Product;

//crea automaticamente un bean JpaRepository
public interface IProductRepository extends JpaRepository<Product, String>{
	
	List<Product> findByCategory(Category category);
	
	//CON JPQL
	/*@Query("FROM Product WHERE name LIKE %:product%")
	List<Product> buscarProductosByProduct(@Param("product") String product);*/
	
	//CON SQL NATIVO
	@Query(value="SELECT * FROM products WHERE name LIKE %:product%",nativeQuery = true) //nativeQuery para especificar que es una sentencia sql nativa 
	List<Product> buscarProductosByProduct(@Param("product") String product);
	
}

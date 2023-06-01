package com.store.product.service;

import java.util.List;

import com.store.product.entity.Category;
import com.store.product.entity.Product;

public interface IProductService {

	List<Product> listProducts();
	
	Product registerProduct(Product product);
	
	Product findProduct(String sku);
	
	Product updateProduct(Product product);
	
	void deleteProduct(String sku);
	
	List<Product> findByCategory(Category category);
	
	List<Product> buscarProductos(String name);
	
	
	//DELETE FISICO - SE BORRAN LOS DATOS DE BD PERMANENTEMENTE 
	//DELETE LOGICO - NO SE BORRAN LOS DATOS, SOLO SE ABANDERAN 
}

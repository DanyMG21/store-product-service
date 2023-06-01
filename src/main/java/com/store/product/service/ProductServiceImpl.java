package com.store.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.product.entity.Category;
import com.store.product.entity.Product;
import com.store.product.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired //busca el bean creado por JpaRepository
	private IProductRepository productRepository;

	@Override
	public List<Product> listProducts() {
		//findAll() hace select * from
		List<Product> lista = productRepository.findAll().stream().filter(x -> x.getStatus().equals("DISPONIBLE")).collect(Collectors.toList()); //Pasar a serviceImpl

		//return productRepository.findAll();
		return lista;
		/*if(productRepository.findAll()!=null) {
			return ResponseEntity.ok(productRepository.findAll());
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}*/
		
		
	}

	@Override
	public Product registerProduct(Product product) {
		//save es como un insert o update
		product.setStatus("DISPONIBLE");
		product.setCreateAt(LocalDateTime.now());
		return productRepository.save(product);
	}

	@Override
	public Product findProduct(String sku) {
		//orElse para devolver algo si no existe
		return productRepository.findById(sku).orElse(null);
	}

	@Override
	public Product updateProduct(Product product) {
		Product productBD = findProduct(product.getSku());
		
		if(productBD!=null) {
			if(product.getStock()<1) 
			product.setStatus("NO DISPONIBLE");
			else
				product.setStatus(productBD.getStatus());
			
		    product.setCreateAt(LocalDateTime.now());
			return productRepository.save(product);
		}
		
		
		return null;
	}

	@Override
	public void deleteProduct(String sku) {
		
		//productRepository.deleteById(sku); -> Borrado fisico
		
		//Borrado logico
		Product productDB = findProduct(sku);
		
		if(productDB!=null) {
			productDB.setStatus("NO DISPONIBLE");
			productDB.setCreateAt(LocalDateTime.now());
			productRepository.save(productDB);
			
		}
		
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> buscarProductos(String name) {
	return productRepository.buscarProductosByProduct(name);
		
	}
	
	//esteriotipos: @Service, @Component, @Repository, @Controller - Crean un bean en el contenedor de spring - son iguales
	//Nos ahorran crear objetos 
}

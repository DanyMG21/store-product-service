package com.store.product.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="products")
@Getter
@Setter
public class Product {

	@Id
	private String sku;
	
	private String name;
	
	private String description;
	
	@NotNull(message = "El precio no puede ser nulo") //valida que no sea nulo -> arroja un bad request cuando es null
	private Float price;
	
	//private float price;
	
	private String status;
	
	private Integer stock;
	
	//private int stock;
	
	//DEFINE UNA LLAVE FORANEA (RELACION N-1)
	@ManyToOne(fetch= FetchType.LAZY)
	//COLUMNA QUE VA A HACER LA CONEXION CON OTRA TABLA
	@JoinColumn(name="categori_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	private Category category;
	
	private LocalDateTime createAt;
	
}


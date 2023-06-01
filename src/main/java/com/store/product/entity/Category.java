package com.store.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//CLASE QUE JAVA VA A UTILIZAR PARA REPRESENTAR UNA TABLA 
@Entity
//PERMITE DEFINIR EL NOMBRE CON EL CUAL SE VA A CREAR LA TABLA
@Table(name="categorias")
//CREAR GETTERS AND SETTERS CON LOMBOK
@Getter
@Setter
public class Category {
	
	//EXPRESA UNA LLAVE PRIMARIA
	@Id
	//DEFINIR QUE SEA AUTO-INCREMENTAL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//PARA DEFINIR EL NOMBRE DE LA COLUMNA 
	@Column(name="name")
	private String name;

}

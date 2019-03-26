package com.lyn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lyn.model.enums.ProductType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author    Yaning Liu
 *
 * @filename  Product.java
 *
 * @date      2019-01-25
 *
 */
@Entity
@Table(name="_Product")
@Setter
@Getter
@NoArgsConstructor
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	private String name;
	

	private int quality;
	@Enumerated(EnumType.STRING)
	private ProductType product_type;

	/**
	 * @param id
	 * @param name
	 * @param quality
	 * @param product_type
	 */
	public Product(long id, String name, int quality, ProductType product_type) {
		super();
		this.id = id;
		this.name = name;
		this.quality = quality;
		this.product_type = product_type;
	}
	

	
}

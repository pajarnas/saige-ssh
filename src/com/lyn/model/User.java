package com.lyn.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="_USER")
@Getter
@Setter
@NoArgsConstructor
public class User{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="_ID",nullable=false,unique=true)
	private long id;
	

	private String name;


	private String password;
	

	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String email;
	

	private String phone;
	

	private String city;
	

	private String date;
	
	public User(String name,String password,String email,Role role,String date,String city){
		super();
		this.name=name;
		this.password=password;
	    this.date=date;
	    this.role=role;
	    this.city=city;
		this.email=email;
	}
	
}

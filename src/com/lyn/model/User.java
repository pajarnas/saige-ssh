package com.lyn.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.lyn.model.enums.Role;
import com.lyn.model.enums.TaskType;

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
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="user")
	@Column(nullable=true)
    private List<Task> sub_tasks;
	
	private String email;
	

	private String phone;
	

	private String city;
	

	private String date;


	/**
	 * @param id
	 * @param name
	 * @param password
	 * @param role
	
	 * @param email
	 * @param phone
	 * @param city
	 * @param date
	 */
	public User(long id, String name, String password, Role role,  String email, String phone,
			String city, String date) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.date = date;
	}



	
}

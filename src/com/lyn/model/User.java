package com.lyn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="S_USER")
public class User{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="U_ID",nullable=false,unique=true)
	private long id;
	
	@Column(name="U_NAME")
	private String name;

	//���ֶ���'U_PASSWORD'ӳ�䵽S_USER����
	@Column(name="U_PASSWORD")
	private String password;
	
	//Ĭ�����ֶ���'sex'ӳ�䵽S_USER����
	private String sex;
	
	//�����������ݿ�
	@Transient
	private String email;
	
	public User(String name,String password,String sex,String email){
		super();
		this.name=name;
		this.password=password;
		this.sex=sex;
		this.email=email;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

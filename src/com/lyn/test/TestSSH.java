package com.lyn.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyn.model.User;


public class TestSSH{

	private ApplicationContext ctx = null;
	
	
	public void testDataSource(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("应用上下文-----"+ctx+"----");
		DataSource dataSource = ctx.getBean(DataSource.class);
		try {
			System.out.println("数据源：-----"+dataSource.getConnection().toString()+"-----");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
		System.out.println("sessionFactory:____"+sessionFactory+"_______sessionFactory");
		Session session = sessionFactory.openSession();
		session.close();
	}
	
	@Test
	public void testlist(){
		ss s1 = new ss(1,2);
		ss s2 = new ss(2,3);
		List<ss> lines = Arrays.asList(s1,s2);
	     List<ss> result = lines.stream()                // convert list to stream
	                .filter(line -> "1".equals(line.getS()))     // we dont like mkyong
	                .collect(Collectors.toList());              // collect the output and convert streams to a List

	        result.forEach(System.out::println);                //output : spring, node

	    
	}
}
class ss{
	private int s;
	private int ss;
	/**
	 * @return the s
	 */
	public int getS() {
		return s;
	}
	/**
	 * @param s the s to set
	 */
	public void setS(int s) {
		this.s = s;
	}
	/**
	 * @return the ss
	 */
	public int getSs() {
		return ss;
	}
	/**
	 * @param ss the ss to set
	 */
	public void setSs(int ss) {
		this.ss = ss;
	}
	/**
	 * @param s
	 * @param ss
	 */
	public ss(int s, int ss) {
		super();
		this.s = s;
		this.ss = ss;
	}
	
}

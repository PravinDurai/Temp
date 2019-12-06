package com.springhibernate.demo5.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springhibernate.demo_5.entity.Customer;

@Component
public class CustomerBean {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	public CustomerBean() {
		super();
	}
	
	public CustomerBean(Customer cus) {
		this.id=cus.getId();
		this.firstName=cus.getFirstName();
		this.lastName=cus.getLastName();
		this.email=cus.getEmail();
	}
	
	public CustomerBean(String firstName, String lastName, String eMail) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=eMail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer Bean [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}

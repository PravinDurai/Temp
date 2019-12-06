package com.springhibernate.demo_5.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.springhibernate.demo5.bean.CustomerBean;
import com.springhibernate.demo_5.entity.Customer;

@Component
public class CustomerModel {
	
	private int id;
	@NotNull(message="First Name is Mandatory Field")
	@Size(min=4)
	private String firstName;
	@NotNull(message="Last Name is Mandatory Field")
	@Size(min=4)
	private String lastName;
	@NotNull(message="E-Mail is Mandatory Field")
	private String email;
	
	public CustomerModel() {
		super();
	}

	public CustomerModel(int id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
		
	public CustomerModel(CustomerBean customerBean) {
		super();
		this.id=customerBean.getId();
		this.firstName = customerBean.getFirstName();
		this.lastName = customerBean.getLastName();
		this.email = customerBean.getEmail();
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
		return("CustomerModel [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]");
	}

}

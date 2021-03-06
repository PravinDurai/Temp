package com.springhibernate.demo_5.dao;

import java.util.List;

import com.springhibernate.demo5.bean.CustomerBean;
import com.springhibernate.demo_5.entity.Customer;
import com.springhibernate.demo_5.model.CustomerModel;

public interface Dao {
	
	public List<Customer> getCustomers();
	
	public boolean createCustomer(CustomerModel customerModel);
	
	public CustomerBean getCustomer(int id);
	
	public void deleteCustomer(int id);

}

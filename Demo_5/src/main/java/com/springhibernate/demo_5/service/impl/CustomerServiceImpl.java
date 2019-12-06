package com.springhibernate.demo_5.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhibernate.demo_5.entity.Customer;
import com.springhibernate.demo_5.model.CustomerModel;
import com.springhibernate.demo5.bean.CustomerBean;
import com.springhibernate.demo_5.dao.Dao;
import com.springhibernate.demo_5.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private Dao customerDao;

	@Transactional
	public List<Customer> getCustomers() {
		return (customerDao.getCustomers());
	}

	@Transactional
	public boolean createCustomer(CustomerModel customerModel) {
		boolean op = false;
		Logger logger = Logger.getLogger(CustomerServiceImpl.class);
		try {
			op = customerDao.createCustomer(customerModel);
			if (!op) {
				logger.info("Created/Updated the Customer Info Successfully !!!");
			} else {
				logger.info("Some issues in creating New Customer...");
			}
		} catch (Exception exc) {
			logger.error(
					"Caught in Exception inside Class :\tCustomerServiceImpl :\tMethod :\tcreateCustomer\nAnd the Exception is :\t"
							+ exc,
					exc);
			exc.printStackTrace();
		} finally {
			return (op);
		}
	}

	@Transactional
	public CustomerBean getCustomer(int id) {
		CustomerBean customerBean = null;
		Logger logger = Logger.getLogger(CustomerServiceImpl.class);
		try {
			customerBean = customerDao.getCustomer(id);
		} catch (Exception exc) {
			logger.error(
					"Caught in Exception inside Class :\tCustomerServiceImpl\tMethod :\tgetCustomer\nAnd the exception is :\t"
							+ exc,
					exc);
			exc.printStackTrace();
		} finally {
			return (customerBean);
		}
	}

	@Transactional
	public void deleteCustomer(int id) {
		Logger logger = Logger.getLogger(CustomerServiceImpl.class);
		try {
			customerDao.deleteCustomer(id);
		} catch (Exception exc) {
			logger.error("Caught in exception inside class :\t CustomerServiceImpl\tMethod :\tdeleteCustomer\nAnd the Exception is :\t"+ exc);
			exc.printStackTrace();
		}
	}

}

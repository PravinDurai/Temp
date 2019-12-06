package com.springhibernate.demo_5.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

import com.springhibernate.demo5.bean.CustomerBean;
import com.springhibernate.demo_5.dao.Dao;
import com.springhibernate.demo_5.entity.Customer;
import com.springhibernate.demo_5.model.CustomerModel;

@Repository
public class CustomerDaoImpl implements Dao {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
			.buildSessionFactory();

	public List<Customer> getCustomers() {
		Session session = factory.getCurrentSession();
		List<Customer> customerList = new ArrayList<Customer>();
		Logger logger = Logger.getLogger(CustomerDaoImpl.class);
		try {
			session.beginTransaction();
			customerList = session.createQuery(" from Customer order by id").list();
			logger.info("\nCustomer Info\n" + customerList);
			session.getTransaction().commit();
		} catch (Exception exc) {
			logger.error(
					"Caught in Exception inside Class :\tCustomerDaoImpl\tMethod :\tgetAllCustomers\nAnd the Exception is :\t"
							+ exc + "\n");
			exc.printStackTrace();
		} finally {
			return (customerList);
		}
	}

	public boolean createCustomer(CustomerModel customerModel) {
		Session session = factory.getCurrentSession();
		Logger logger = Logger.getLogger(CustomerDaoImpl.class);
		boolean op = false;
		try {
			session.beginTransaction();
			Customer customer = new Customer(customerModel);
			session.saveOrUpdate(customer);
			logger.info("\nThe New Customer Info is \nid :\t"+customerModel.getId()+"\tFirst Name :\t" + customerModel.getFirstName() + "\tLast Name :\t"
					+ customerModel.getLastName() + "\tE - Mail :\t" + customerModel.getEmail() + "\n");
			session.getTransaction().commit();
		} catch (Exception exc) {
			op = true;
			logger.error(
					"Caught in Exception inside Class :\tCustomerDaoImpl\tMethod :\tcreateCustomer\nAnd the Exception is :\t"
							+ exc,
					exc);
			exc.printStackTrace();
		} finally {
			return (op);
		}
	}

	public CustomerBean getCustomer(int id) {
		CustomerBean customerBean=null;
		Session session=factory.getCurrentSession();
		Logger logger=Logger.getLogger(CustomerDaoImpl.class);
		try {
			session.beginTransaction();
			Customer customer =(Customer) session.get(Customer.class,id);
			customerBean=new CustomerBean(customer);
			session.getTransaction().commit();
		} catch (Exception exc) {
			logger.error("Caught in Exception inside Class :\tCustomerDaoImpl\tMethod :\tCustomerBean\nAnd the Exception is :\n"+exc,exc);
			exc.printStackTrace();
		} finally {
			return (customerBean);
		}
	}
	
	public void deleteCustomer(int id) {
		Session session=factory.getCurrentSession();
		
		//Creating a Query Parameter so that we can create a HQL Query to delete the 
		//Customer based on the input parameter, In this case it the ID.
		//Here the Value of :id should be same name as that of the Customer Entity
		
		Logger logger=Logger.getLogger(CustomerDaoImpl.class);
		try {
			session.beginTransaction();
			
			Query query=session.createQuery("delete from Customer where id=:id");
			query.setParameter("id", id);	
			//Execute update is more generic and it works for both Update and delete operations
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(Exception exc) {
			logger.info("Caught in Exception inside Class :\tCustomerDaoImpl\tMethod :\tdeleteCustomer\nAnd the Exception is :\t"+exc);
			exc.printStackTrace();
		}
	}
}

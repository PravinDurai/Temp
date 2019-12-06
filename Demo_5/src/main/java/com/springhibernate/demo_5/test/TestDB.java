
package com.springhibernate.demo_5.test;
/*

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDB
 
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Setup Connection Variables
		String userName="Pravin";
		String password="hibernate@1234";
		String connectionUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver="com.mysql.jdbc.Driver";
		Connection connection;
		//Connect to Database
		try{
			PrintWriter out=response.getWriter();
			out.println("Connecting to JDBC :\t"+connectionUrl);
			Class.forName(driver);
			connection=DriverManager.getConnection(connectionUrl,userName,password);
			out.println("SUCCESS!!!");
			connection.close();
		}catch(Exception exc){
			System.out.println("Caught in exception inside class :\tTestDB\tMethod :\tdoGet\nAnd the Exeption is :\t"+exc+"\n");
			exc.printStackTrace();
		}
	}

}
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.ArrayList;

import com.springhibernate.demo_5.entity.Customer;

public class TestDB {
	public static void main(String arg[]) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			List<Customer> customerList = session.createQuery(" from Customer").list();
			System.out.println("Customers are \n" + customerList);
			session.getTransaction().commit();
		} catch (Exception exc) {
			System.out.println("Caught in exception inside class :\tTestDB\tMethod :\tMain\nAnd the exception is :\t"
					+ exc + "\n");
			exc.printStackTrace();
		} finally {
			factory.close();
		}
	}
}
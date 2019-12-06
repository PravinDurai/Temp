package com.springhibernate.demo_5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.Valid;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springhibernate.demo5.bean.CustomerBean;
import com.springhibernate.demo_5.dao.Dao;
import com.springhibernate.demo_5.entity.Customer;
import com.springhibernate.demo_5.model.CustomerModel;
import com.springhibernate.demo_5.service.CustomerService;
import com.springhibernate.demo_5.service.impl.CustomerDaoImpl;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

//	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ApplicationContext.class);
//	CustomerDaoImpl cusDaoImpl=context.getBean("CustomerDaoImpl",CustomerDaoImpl.class);

	@Autowired
	private CustomerService customerService;
	private int id;
	private String firstName;
	private Model customerModel;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

//	@RequestMapping(value="/list")
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		Logger logger = Logger.getLogger(CustomerController.class);
		List<CustomerBean> customerBeanList = new ArrayList<CustomerBean>();
		for (Customer temp : customerService.getCustomers()) {
			CustomerBean tempCusBean = new CustomerBean(temp);
			customerBeanList.add(tempCusBean);
//			System.out.println(tempCusBean.toString());
			logger.info(tempCusBean.toString());
		}
		theModel.addAttribute("customerBList", customerBeanList);
		return ("list-customer");
	}

	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		CustomerModel customerModel = new CustomerModel();
		model.addAttribute(customerModel);
		return ("AddCustomer");
	}

//	@RequestMapping(value = "/newCustomer", method = RequestMethod.POST)
	@PostMapping(value="/newCustomer")
	public String newCustomer(@Valid @ModelAttribute("customerModel") CustomerModel customerModel,
			BindingResult binding, HttpServletResponse response) {
		boolean error = false;
		Logger logger = Logger.getLogger(CustomerController.class);
		try {
			if (!binding.hasErrors()) {
				error = false;
			} else {
				error = true;
			}
			logger.info("Error Value Method :\tnew Customer" + error);
		} catch (Exception exc) {
			logger.error(
					"Caught in Exception inside Class :\tCustomerController\tMethod :\tnewCustomer\nAnd the Exception is :\t"
							+ exc);
			exc.printStackTrace();
		} finally {
			if (error) {
				return ("AddCustomer");
			} else {
				customerService.createCustomer(customerModel);
				return ("redirect:list");
			}
		}
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,@RequestParam("firstName") String firstName,Model customerModel) {
		CustomerBean customerBean=customerService.getCustomer(id);
		Logger logger=Logger.getLogger(CustomerController.class);
		logger.info("Id :\t"+id+"\tFirst Name :\t"+firstName);
		customerModel.addAttribute("customerModel",customerBean);
		return("AddCustomer");
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id,@RequestParam("firstName") String firstName){
		Logger logger=Logger.getLogger(CustomerController.class);
		logger.info("The Customer Id that's gonna be deleted is :\t"+id+"\tFirst Name :\t"+firstName);
		customerService.deleteCustomer(id);
		
		return("redirect:/customer/list");
	}

}
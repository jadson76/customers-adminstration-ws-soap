package com.jadson.soap.webservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jadson.soap.webservices.bean.Customer;
import com.jadson.soap.webservices.bean.helper.Status;

@Component
public class CustomerDetailService {
	
	private static List<Customer> customers = new ArrayList<>();
	
	static {
		Customer c1 = new Customer(1, "Bob","9999-9999","bob@gmail.com");
		Customer c2 = new Customer(2, "Maria","9996-9999","maria@gmail.com");
		Customer c3 = new Customer(3, "Jose","99997-9999","jose@gmail.com");
		Customer c4 = new Customer(4, "Billy","9998-9999","billy@gmail.com");
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);		
	}
	
	public Customer findbyId(int id) {
		Optional<Customer> customerOp = customers.stream().filter(c -> c.getId() == id).findAny();
		if(customerOp.isPresent()) {
			return customerOp.get();
		}
		return null;
	}
	
	public List<Customer> findAll() {
		return customers;		
	}
	
	public Status deleteById(int id) {
		Optional<Customer> customerOp = customers.stream().filter(c -> c.getId() == id).findAny();
		if(customerOp.isPresent()) {
			customers.remove(customerOp.get());
			return Status.SUCCESS;
		}
		return Status.FAILURE;
	}
	

}

package com.jadson.soap.webservices.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jadson.CustomerDetail;
import com.jadson.DeleteCustomerRequest;
import com.jadson.DeleteCustomerResponse;
import com.jadson.GetAllCustomerDetailRequest;
import com.jadson.GetAllCustomerDetailResponse;
import com.jadson.GetCustomerDetailRequest;
import com.jadson.GetCustomerDetailResponse;
import com.jadson.Status;
import com.jadson.soap.webservices.bean.Customer;
import com.jadson.soap.webservices.exception.CustomerNotFoundException;
import com.jadson.soap.webservices.service.CustomerDetailService;

@Endpoint
public class CustomerDetailEndPoint {
	
	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace = "http://jadson.com", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processaCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) throws Exception {
		
		Customer customer = service.findbyId(req.getId());
		if(customer == null) {
			throw new CustomerNotFoundException("Invalid Customer id "+req.getId());
		}		
		return convertToGetCustomerDetailResponse(customer);
	}
	
	@PayloadRoot(namespace = "http://jadson.com", localPart = "GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse processaGetAllCustomerDetailRequest(@RequestPayload GetAllCustomerDetailRequest req) 
			throws Exception {		
		List<Customer> customers = service.findAll();			
		return convertGetAllCustomerDetailResponse(customers);
	}
	
	@PayloadRoot(namespace = "http://jadson.com", localPart = "DeleteCustomerRequest")
	@ResponsePayload
	public DeleteCustomerResponse deleteCustomerRequest(@RequestPayload DeleteCustomerRequest req) throws Exception {
		DeleteCustomerResponse resp = new DeleteCustomerResponse();
		resp.setStatus(converteStatusSoap(service.deleteById(req.getId())));			
		return resp;
	}	
	
	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		resp.setCustomerDetail(convertToCustomerDetail(customer));
		return resp;
	}
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setEmail(customer.getEmail());
		customerDetail.setPhone(customer.getPhone());
		return customerDetail;
	}
	
	private GetAllCustomerDetailResponse convertGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> resp.getCustomerDetail().add(convertToCustomerDetail(c)));
		return resp;
	}
	
	private com.jadson.Status converteStatusSoap(com.jadson.soap.webservices.bean.helper.Status statusService) {
		if(statusService == com.jadson.soap.webservices.bean.helper.Status.FAILURE) {
			return com.jadson.Status.FAILURE;
		}
		return com.jadson.Status.SUCCESS;
		
	}
	
	

}

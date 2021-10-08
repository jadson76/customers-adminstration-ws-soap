package com.jadson.soap.webservices.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jadson.CustomerDetail;
import com.jadson.GetCustomerDetailRequest;
import com.jadson.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {
	
	@PayloadRoot(namespace = "http://jadson.com", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processaCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) {
		GetCustomerDetailResponse response  = new GetCustomerDetailResponse();
		CustomerDetail customerdetail = new CustomerDetail();
		customerdetail.setId(1);
		customerdetail.setName("Jadson Souza");
		customerdetail.setEmail("teste@jadson.com");
		customerdetail.setPhone("2334-5757");	
		
		response.setCustomerDetail(customerdetail);
		return response;
	}

}

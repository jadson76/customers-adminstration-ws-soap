package com.jadson.soap.webservices.bean;

public class Customer {
	
	private int id;
	private String name;
	private String phone;
	private String email;
	
	
	
	public Customer(int id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPhone() {
		return phone;
	}
	public final void setPhone(String phone) {
		this.phone = phone;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Customer [id=%s, name=%s, phone=%s, email=%s]", id, name, phone, email);
	}
	
	

}

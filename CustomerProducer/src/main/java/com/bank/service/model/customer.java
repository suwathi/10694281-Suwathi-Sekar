package com.bank.service.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")	
public class customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	 private int id;
	
	@Column(name = "name")
	 private String name;
	
	@Column(name = "age")
	 private int age;
	
	@Column(name = "address")
	 private String address;
	
	@Column(name = "typeOfAccount")
	 private String typeOfAccount;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	
	
	@Override
	public String toString() {
		return "customer [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", typeOfAccount="
				+ typeOfAccount + "]";
	}
	 
	 
	
	

}

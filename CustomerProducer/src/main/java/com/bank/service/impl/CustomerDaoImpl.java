package com.bank.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bank.service.dao.BankDaoJpa;
import com.bank.service.model.customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Autowired
	private BankDaoJpa bankDaoJpa;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "callBankServiceAndGetData_Fallback")
	public String getAllAccounts() {
		 LOGGER.info("Getting Bank details ");
		List<ServiceInstance> instances = discoveryClient.getInstances("BANK-ZUUL-SERVICE");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/producer/bank";
		 LOGGER.info("baseUrl : "+baseUrl);
		
		String response="";
		try {
		 response = restTemplate
				.exchange("http://localhost:8086/getAllCustomerDetails"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}).getBody();
		} catch (Exception ex) {
			 LOGGER.info("Exception : "+ex);
		}
		return "NORMAL FLOW !!! -Bank details " + response + " -  " + new Date();
		
	}
	
	
	public String callBankServiceAndGetData_Fallback() {
		 LOGGER.info("Student Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Customer consumer client Service at this moment. Service will be back shortly - " + new Date();
	
	}


	
	@Override
	public customer createAccount(customer cust) {
		return bankDaoJpa.save(cust);
	}

	@Override
	public customer updateAccountById(int accountid,customer customerDetails) {
		customer cust = bankDaoJpa.findById(accountid).get();
		LOGGER.info("customerDetails "+customerDetails);
		cust.setName(customerDetails.getName());
		cust.setAge(customerDetails.getAge());
		cust.setAddress(customerDetails.getAddress());
		cust.setTypeOfAccount(customerDetails.getTypeOfAccount());
		return bankDaoJpa.save(cust);
	}

	@Override
	public String deleteAccount(int accountId) {
		customer cust = bankDaoJpa.findById(accountId).get();
		bankDaoJpa.delete(cust);
		return "Successfully Deleted Account: " ;
	}

	@Override
	public customer getAccount(int id) {
		return bankDaoJpa.findById(id).get();
	}

	@Override
	public List<customer> getAllCustomers() {
		return bankDaoJpa.findAll();
	}

	
}

package com.bank.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.service.impl.CustomerDao;
import com.bank.service.model.customer;




@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	 @GetMapping("/customer")
	    public String getAllAccounts(){
		 String allCustomers;
		 allCustomers = customerDao.getAllAccounts();
		 LOGGER.info("In getAllAccounts "+allCustomers);
	        return allCustomers;
	    }
	 
	 @GetMapping("/customers/{id}")
	    public customer getAccount(@PathVariable int id){
		 LOGGER.info("In getAccountsById "+customerDao.getAllAccounts());
	        return customerDao.getAccount( id );
	       
	    }
	 
	 @PostMapping("/customers")
	    public customer createAccount(@RequestBody customer customer){
		 LOGGER.info("In createAccount "+customerDao.createAccount(customer));
	        return customerDao.createAccount(customer);
	    }
	 
	 @PutMapping("/customers/{accountid}")
	    public customer updateAccountById(@PathVariable int accountid,@RequestBody customer customerDetails){
		 LOGGER.info("In updateAccountById "+accountid +"customerDetails "+customerDetails);
	        return customerDao.updateAccountById(accountid,customerDetails);
	    }
	 
	 @DeleteMapping("/customers/{accountid}")
	    public String deleteTicketById(@PathVariable int accountid){
		 LOGGER.info("In @DeleteMapping ");
	        return customerDao.deleteAccount(accountid);
	    }
	 
	 
	 @GetMapping("/customers")
		public List<customer> getAllCustomers() {
			return customerDao.getAllCustomers();
		}
}

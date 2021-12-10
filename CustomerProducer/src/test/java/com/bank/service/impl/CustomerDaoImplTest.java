package com.bank.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.service.dao.BankDaoJpa;
import com.bank.service.model.customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoImplTest {

	 @MockBean
	 private BankDaoJpa bankDaoJpa;

	 @Autowired
	 private CustomerDao customerDao;
	 
	@Test
	public void testGetAllCustomers() {
		 customer customer1 = new customer();
		    customer1.setId(1);
		    customer1.setName("Suwathi");
		    customer1.setAge(25);
		    customer1.setAddress("coimbatore");
		    customer1.setTypeOfAccount("savings");
		    customer customer2 = new customer();
		    customer2.setId(2);
		    customer2.setName("Sekar");
		    customer2.setAge(30);
		    customer2.setAddress("chennai");
		    customer2.setTypeOfAccount("current");
		    List<customer> customerslist = new ArrayList<customer>();
		    customerslist.add(customer1);
		    customerslist.add(customer2);
	        Mockito.when(bankDaoJpa.findAll()).thenReturn(customerslist);
	        assertThat(customerDao.getAllCustomers()).isEqualTo(customerslist);

	}

	
	@Test
	public void testCreateAccount() {
		 customer customers = new customer();
		 customers.setId(102);
		 customers.setName("Suwathi");
		 customers.setAge(25);
		 customers.setAddress("coimbatore");
		 customers.setTypeOfAccount("savings");
        Mockito.when(bankDaoJpa.save(customers)).thenReturn(customers);
        assertThat(customerDao.createAccount(customers)).isEqualTo(customers);

	}

	@Test
	public void testUpdateAccountById() {
		customer customers = new customer();
		 customers.setId(102);
		 customers.setName("Suwathi");
		 customers.setAge(25);
		 customers.setAddress("coimbatore");
		 customers.setTypeOfAccount("savings");
		 bankDaoJpa.save(customers);
	        Mockito.when(bankDaoJpa.findById(100).get()).thenReturn(customers);
	        customers.setTypeOfAccount("current");
	        Mockito.when(bankDaoJpa.save(customers)).thenReturn(customers);
	        assertThat(customerDao.updateAccountById(102, customers)).isEqualTo(customers);
	   
	}

	@Test
	public void testDeleteAccount() {
		 customer customers = new customer();
		 customers.setId(102);
		 customers.setName("Suwathi");
		 customers.setAge(25);
		 customers.setAddress("coimbatore");
		 customers.setTypeOfAccount("savings");
        
	     Mockito.when(bankDaoJpa.save(customers)).thenReturn(customers);
	     Mockito.when(bankDaoJpa.findById(105).get()).thenReturn(customers);
	     bankDaoJpa.deleteById(customers.getId());
	     Mockito.when(bankDaoJpa.findById(105).get()).thenReturn(customers);
	     Assert.assertNotEquals(customers, new customer());
	     Assert.assertEquals("Successfully Deleted Account:",customerDao.deleteAccount(102));
	}

	@Test
	public void testGetAccount() {
		 customer customers = new customer();
		 customers.setId(102);
		 customers.setName("Suwathi");
		 customers.setAge(25);
		 customers.setAddress("coimbatore");
		 customers.setTypeOfAccount("savings");
	     customer tt=bankDaoJpa.findById(102).get();
	        Mockito.when(tt).thenReturn(customers);
	        assertThat(customerDao.getAccount(102)).isEqualTo(customers);
	}

	

}

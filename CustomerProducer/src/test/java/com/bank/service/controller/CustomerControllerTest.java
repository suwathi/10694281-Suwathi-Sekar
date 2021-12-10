package com.bank.service.controller;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bank.service.impl.CustomerDao;
import com.bank.service.model.customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerControllerTest.class)
public class CustomerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	

    @MockBean
    private CustomerDao customerDao;
    
 
	@Test
	public void testGetAllCustomers() throws Exception {
		String URI = "/bank/customers";
	    customer customer1 = new customer();
	    customer1.setId(102);
	    customer1.setName("Suwathi");
	    customer1.setAge(25);
	    customer1.setAddress("coimbatore");
	    customer1.setTypeOfAccount("savings");
	    customer customer2 = new customer();
	    customer2.setId(103);
	    customer2.setName("Sekar");
	    customer2.setAge(30);
	    customer2.setAddress("chennai");
	    customer2.setTypeOfAccount("current");
	    List<customer> customerslist = new ArrayList<customer>();
	    customerslist.add(customer1);
	    customerslist.add(customer2);
	    String jsonInput = this.converttoJson(customerslist);
	    Mockito.when(customerDao.getAllCustomers()).thenReturn(customerslist);
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    String jsonOutput = mockHttpServletResponse.getContentAsString();
	    assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	public void testGetAccount() throws Exception {
	     String URI= "/booking/getAccount/customers/{id}";
	     customer customers = new customer();
	     customers.setId(102);
	     customers.setName("Suwathi");
	     customers.setAge(25);
	     customers.setAddress("coimbatore");;
	     customers.setTypeOfAccount("savings");;
	     String jsonInput = this.converttoJson(customers);
	     Mockito.when(customerDao.getAccount(Mockito.any())).thenReturn(customers);
	     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);		
	}

	@Test
	public void testCreateAccount() throws Exception {
		 String URI = "/bank/customers";
		 customer customers = new customer();
		 customers.setId(102);
		 customers.setName("Suwathi");
		 customers.setAge(25);
		 customers.setAddress("coimbatore");
		 customers.setTypeOfAccount("savings");
	     String jsonInput = this.converttoJson(customers);
	     Mockito.when(customerDao.createAccount(Mockito.any(customer.class))).thenReturn(customers);
	     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testUpdateAccountById() throws Exception {

        String URI = "/customers/{accountid}";
        customer customer1 = new customer();
	    customer1.setId(102);
	    customer1.setName("Suwathi");
	    customer1.setAge(25);
	    customer1.setAddress("coimbatore");
	    customer1.setTypeOfAccount("savings");
        String jsonInput = this.converttoJson(customer1);
        Mockito.when(customerDao.updateAccountById(Mockito.any(),Mockito.any())).thenReturn(customer1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,customer1,102).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput.substring(0,5)).isEqualTo(jsonOutput.substring(0,5));
	}

	@Test
	public void testDeleteTicketById() throws Exception {
		  String URI = "/customers/{accountid}";
		  customer customer1 = new customer();
		    customer1.setId(102);
		    customer1.setName("Suwathi");
		    customer1.setAge(25);
		    customer1.setAddress("coimbatore");
		    customer1.setTypeOfAccount("savings");
	        Mockito.when(customerDao.getAccount(Mockito.any())).thenReturn(customer1);
	        Mockito.when(customerDao.deleteAccount(Mockito.any())).thenReturn("Successfully Deleted Account: ");
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.
	        		APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	
	
	 private String converttoJson(Object ticket) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(ticket);
	    }

}

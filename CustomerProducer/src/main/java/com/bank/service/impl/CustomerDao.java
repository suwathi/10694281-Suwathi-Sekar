package com.bank.service.impl;

import java.util.List;

import com.bank.service.model.customer;


public interface CustomerDao {

	String getAllAccounts();

	customer createAccount(customer customer);

	customer updateAccountById(int accountid, customer customerDetails);

	String deleteAccount(int accountId);

	customer getAccount(int id);

	List<customer> getAllCustomers();

}

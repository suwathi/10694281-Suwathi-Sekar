package com.bank.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.service.model.customer;


@Repository
public interface BankDaoJpa extends JpaRepository<customer, Integer> {

}

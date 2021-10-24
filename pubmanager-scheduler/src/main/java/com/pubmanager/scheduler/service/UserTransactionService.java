package com.pubmanager.scheduler.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubmanager.scheduler.model.UserTransaction;
import com.pubmanager.scheduler.repositories.UserTransactionsRepository;

@Service
public class UserTransactionService implements IUserTransactionService {
	
	 @Autowired
	 private UserTransactionsRepository repository;

	@Override
	public List<UserTransaction> findAll() {

        var userTransactions = (List<UserTransaction>) repository.findAll();

        return userTransactions;
	}
	

}

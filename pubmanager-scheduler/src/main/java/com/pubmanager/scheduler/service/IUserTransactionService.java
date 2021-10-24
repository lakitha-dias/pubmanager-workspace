package com.pubmanager.scheduler.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.Query;

import  com.pubmanager.scheduler.model.UserTransaction;

public interface IUserTransactionService {
	
	@Query("Select * from UserTransaction transaction join UserCategory category on transaction.categoryId = category.categoryId")
    List<UserTransaction> findAll();
   
}

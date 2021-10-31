package com.pubmanager.expensetracker.repositories;

import com.pubmanager.expensetracker.domain.Transaction;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate, Timestamp transactionLoggedDate, String transactionExpenseSource) throws EtBadRequestException;

    void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    
    // All transactions for given category id and date range
    List<Transaction> findAllTransactionsByCategoryDateTime(Integer userId, Integer categoryId, Timestamp startDateTime, Timestamp endDateTime);
    
    // All transactions for given  date range
    List<Transaction> findAllTransactionsByDateTime(Integer userId, Timestamp startDateTime, Timestamp endDateTime);

}

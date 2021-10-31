package com.pubmanager.expensetracker.services;

import com.pubmanager.expensetracker.domain.Transaction;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate,Timestamp transactionLoggedDate, String transactionExpenseSource) throws EtBadRequestException;

    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
    
    // All transactions for given category id and date range
    List<Transaction> findAllTransactionsByCategoryDateTime(Integer userId, Integer categoryId, Timestamp startDateTime, Timestamp endDateTime) throws EtBadRequestException;
    
    // All transactions for given  date range
    List<Transaction> findAllTransactionsByDateTime(Integer userId,  Timestamp startDateTime, Timestamp endDateTime) throws EtBadRequestException;

}

package com.pubmanager.expensetracker.services;

import com.pubmanager.expensetracker.domain.Transaction;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;
import com.pubmanager.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
        return transactionRepository.findAll(userId, categoryId);
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate, Timestamp transactionLoggedDate) throws EtBadRequestException {
        int transactionId = transactionRepository.create(userId, categoryId, amount, note, transactionDate,transactionLoggedDate);
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException {
        transactionRepository.update(userId, categoryId, transactionId, transaction);
    }

    @Override
    public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        transactionRepository.removeById(userId, categoryId, transactionId);
    }

    
    // All transactions for given category id and date range
	@Override
	public List<Transaction> findAllTransactionsByCategoryDateTime(Integer userId, Integer categoryId, Timestamp startDateTime, Timestamp endDateTime)
			throws EtBadRequestException {
		return transactionRepository.findAllTransactionsByCategoryDateTime(userId, categoryId, startDateTime,endDateTime);
	}

	
	// All transactions for given  date range
	@Override
	public List<Transaction> findAllTransactionsByDateTime(Integer userId, Timestamp startDateTime, Timestamp endDateTime) throws EtBadRequestException {
		return transactionRepository.findAllTransactionsByDateTime(userId, startDateTime,endDateTime);
	}
    
    
}

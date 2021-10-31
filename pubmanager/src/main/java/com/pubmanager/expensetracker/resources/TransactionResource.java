package com.pubmanager.expensetracker.resources;

import com.pubmanager.expensetracker.domain.Transaction;
import com.pubmanager.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request,
                                                                @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("categoryId") Integer categoryId,
                                                          @PathVariable("transactionId") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) throws ParseException {
        int userId = (Integer) request.getAttribute("userId");
        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transactionDate = (Long) transactionMap.get("transactionDate");
        String transactionLoggedDateString = (String) transactionMap.get("transactionLoggedDate");
        String transactionExpenseSource = (String) transactionMap.get("transactionExpenseSource");
        

        SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date transactionDateConverted = null;
        java.sql.Timestamp transactionDateSQL = null;
        try {
        	transactionDateConverted =  changeFormat.parse(transactionLoggedDateString);
        	transactionDateSQL = new java.sql.Timestamp(transactionDateConverted.getTime());

        } catch (ParseException e) {
             e.getMessage();
        }
        
        
        Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate,transactionDateSQL,transactionExpenseSource);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId,
                                                                  @RequestBody Transaction transaction) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.updateTransaction(userId, categoryId, transactionId, transaction);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.removeTransaction(userId, categoryId, transactionId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    
    // All transactions for given category id and date range
    @PostMapping("find-transactions-by-category-datetime")
    public  ResponseEntity<List<Transaction>> findAllTransactionsByCategoryDateTime(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) {
        int userId = (Integer) request.getAttribute("userId");
        String startDateTime = (String) transactionMap.get("startDateTime");
        String endDateTime = (String) transactionMap.get("endDateTime");
        
        
        SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date transactionDateConverted1 = null;
        java.util.Date transactionDateConverted2 = null;
        
        java.sql.Timestamp transactionDateSQL1 = null;
        java.sql.Timestamp transactionDateSQL2 = null;
        
        try {
        	transactionDateConverted1 =  changeFormat.parse(startDateTime);
        	transactionDateConverted2 =  changeFormat.parse(endDateTime);
        	
        	transactionDateSQL1 = new java.sql.Timestamp(transactionDateConverted1.getTime());
        	transactionDateSQL2 = new java.sql.Timestamp(transactionDateConverted2.getTime());
        	

        } catch (ParseException e) {
             e.getMessage();
        }
        
        
        List<Transaction> transactions = transactionService.findAllTransactionsByCategoryDateTime(userId, categoryId,transactionDateSQL1,transactionDateSQL2);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    
    
    // /api/categories/null/transactions/find-transactions-by-datetime
    
    ///api/categories/{categoryId}/transactions/find-transactions-by-category-datetime
    // All transactions for given  date range
    @PostMapping("find-transactions-by-datetime")
    public ResponseEntity<List<Transaction>> findAllTransactionsByDateTime(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) {
    	 int userId = (Integer) request.getAttribute("userId");
    	 String startDateTime = (String) transactionMap.get("startDateTime");
    	 String endDateTime = (String) transactionMap.get("endDateTime");
    	 
    	 
    	  SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

          java.util.Date transactionDateConverted1 = null;
          java.util.Date transactionDateConverted2 = null;
          
          java.sql.Timestamp transactionDateSQL1 = null;
          java.sql.Timestamp transactionDateSQL2 = null;
          
          try {
          	transactionDateConverted1 =  changeFormat.parse(startDateTime);
          	transactionDateConverted2 =  changeFormat.parse(endDateTime);
          	
          	transactionDateSQL1 = new java.sql.Timestamp(transactionDateConverted1.getTime());
          	transactionDateSQL2 = new java.sql.Timestamp(transactionDateConverted2.getTime());
          	

          } catch (ParseException e) {
               e.getMessage();
          }
         
         
         List<Transaction> transactions = transactionService.findAllTransactionsByDateTime(userId,transactionDateSQL1,transactionDateSQL2);
         return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}

package com.pubmanager.expensetracker.resources;

import com.pubmanager.expensetracker.domain.BillReminderItem;
import com.pubmanager.expensetracker.services.BillReminderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills")
public class BillReminderItemResource {

    @Autowired
    BillReminderItemService billReminderItemService;

    @GetMapping("")
    public ResponseEntity<List<BillReminderItem>> getAll(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<BillReminderItem> billReminderItems = billReminderItemService.fetchAll(userId);
        return new ResponseEntity<>(billReminderItems, HttpStatus.OK);
    }
    
    @GetMapping("/{itemId}")
    public ResponseEntity<BillReminderItem> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("categoryId") Integer itemId) {
        int userId = (Integer) request.getAttribute("userId");
        BillReminderItem billReminderItem = billReminderItemService.fetchById(userId, itemId);
        return new ResponseEntity<>(billReminderItem, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BillReminderItem> addItem(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
    	
    	int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        Double amount = Double.valueOf(categoryMap.get("amount").toString());
        String dueDate = (String) categoryMap.get("due_date");
        
        
        SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        java.util.Date transactionDateConverted = null;
        java.sql.Timestamp transactionDateSQL = null;
        try {
        	transactionDateConverted =  changeFormat.parse(dueDate);
        	transactionDateSQL = new java.sql.Timestamp(transactionDateConverted.getTime());

        } catch (ParseException e) {
             e.getMessage();
        }
        
        BillReminderItem billReminderItem = billReminderItemService.addItem(title, amount,transactionDateSQL,userId);
        return new ResponseEntity<>(billReminderItem, HttpStatus.CREATED);
    }

}

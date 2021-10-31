package com.pubmanager.expensetracker.services;

import com.pubmanager.expensetracker.domain.BillReminderItem;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;

import java.sql.Timestamp;
import java.util.List;

public interface BillReminderItemService {

    List<BillReminderItem> fetchAll(Integer userId);
    
    BillReminderItem fetchById(Integer userId, Integer itemId) throws EtResourceNotFoundException;

    BillReminderItem addItem(String title, double amount, Timestamp due_date,Integer userId) throws EtBadRequestException;

}

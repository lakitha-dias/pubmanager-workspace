package com.pubmanager.expensetracker.repositories;

import com.pubmanager.expensetracker.domain.BillReminderItem;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;
import java.sql.Timestamp;
import java.util.List;

public interface BillReminderItemRepository {

    List<BillReminderItem> findAll(Integer userId) throws EtResourceNotFoundException;

    BillReminderItem findById(Integer userId, Integer itemId) throws EtResourceNotFoundException;
    
    Integer create(String title, double amount, Timestamp due_date, int userId) throws EtBadRequestException;

}

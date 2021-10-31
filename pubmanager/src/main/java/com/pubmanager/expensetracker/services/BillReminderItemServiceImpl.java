package com.pubmanager.expensetracker.services;

import com.pubmanager.expensetracker.domain.BillReminderItem;
import com.pubmanager.expensetracker.domain.Category;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;
import com.pubmanager.expensetracker.repositories.BillReminderItemRepository;
import com.pubmanager.expensetracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class BillReminderItemServiceImpl implements BillReminderItemService {

    @Autowired
    BillReminderItemRepository billReminderItemRepository;

    @Override
    public List<BillReminderItem> fetchAll(Integer userId) {
        return billReminderItemRepository.findAll(userId);
    }
    
    @Override
    public BillReminderItem fetchById(Integer userId, Integer itemId) throws EtResourceNotFoundException {
        return billReminderItemRepository.findById(userId, itemId);
    }


    @Override
    public BillReminderItem addItem(String title, double amount, Timestamp due_date,Integer userId) throws EtBadRequestException {
        int id = billReminderItemRepository.create(title, amount, due_date,userId);
        return billReminderItemRepository.findById(userId, id);
    }

}

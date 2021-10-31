package com.pubmanager.expensetracker.repositories;

import com.pubmanager.expensetracker.domain.BillReminderItem;
import com.pubmanager.expensetracker.exceptions.EtBadRequestException;
import com.pubmanager.expensetracker.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BillReminderItemRepositoryImpl implements BillReminderItemRepository {

    private static final String SQL_FIND_ALL = "SELECT C.ID, C.TITLE, C.AMOUNT, C.DUE_DATE, " +
            "FROM ET_BILL_REMINDERS T";
    
    private static final String SQL_FIND_BY_ID = "SELECT C.ID, C.TITLE, C.AMOUNT, C.DUE_DATE " +
            "FROM ET_BILL_REMINDERS T  WHERE USER_ID = ? AND ID = ?";
    
    private static final String SQL_CREATE = "INSERT INTO ET_BILL_REMINDERS (ID, TITLE, AMOUNT, DUE_DATE, USER_ID) VALUES(NEXTVAL('ET_BILL_REMINDERS_SEQ'), ?, ?, ?, ?,?)";
  
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<BillReminderItem> findAll(Integer userId) throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId}, billReminderItemRowMapper);
    }
    
    @Override
    public BillReminderItem findById(Integer userId, Integer itemId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, itemId}, billReminderItemRowMapper);
        }catch (Exception e) {
            throw new EtResourceNotFoundException("Item not found");
        }
    }


    @Override
    public Integer create(String title, double amount,Timestamp due_date,int userId) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setDouble(2, amount);
                ps.setTimestamp(3, due_date);
                ps.setInt(4, userId);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    
    private RowMapper<BillReminderItem> billReminderItemRowMapper = ((rs, rowNum) -> {
        return new BillReminderItem(rs.getInt("ID"),
                rs.getString("TITLE"),
                rs.getDouble("AMOUNT"),
                rs.getTimestamp("DUE_DATE"),
                rs.getInt("USER_ID"));
    });
    

}

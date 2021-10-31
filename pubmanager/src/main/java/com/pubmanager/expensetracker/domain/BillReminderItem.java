package com.pubmanager.expensetracker.domain;

import java.sql.Timestamp;

public class BillReminderItem {
	
	
      private int id;
      private String title;
      private double amount;
      private Timestamp due_date;
      private int userId;
      

      public BillReminderItem(int id, String title, double amount, Timestamp due_date, int userId) {
  		this.id = id;
  		this.title = title;
  		this.amount = amount;
  		this.due_date = due_date;
  		this.userId = userId;
  	}
      
      
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getDue_date() {
		return due_date;
	}
	public void setDue_date(Timestamp due_date) {
		this.due_date = due_date;
	}
      
      
}

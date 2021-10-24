package com.pubmanager.scheduler.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="et_transactions")
public class UserTransaction {
	     
	    @Id
	    private Integer transactionId;
	    
		@OneToOne(fetch = FetchType.LAZY, optional = false,targetEntity = UserCategory.class)
		@Fetch(FetchMode.JOIN)
		@JoinColumn(name="category_id")
	    private Integer categoryId;
		
	    private Integer userId;
	    private Double amount;
	    private String note;
	    private Long transactionDate;


	    public Integer getTransactionId() {
	        return transactionId;
	    }



	    public Integer getCategoryId() {
	        return categoryId;
	    }



	    public Integer getUserId() {
	        return userId;
	    }


	    public Double getAmount() {
	        return amount;
	    }



	    public String getNote() {
	        return note;
	    }

	    public Long getTransactionDate() {
	        return transactionDate;
	    }

		
}

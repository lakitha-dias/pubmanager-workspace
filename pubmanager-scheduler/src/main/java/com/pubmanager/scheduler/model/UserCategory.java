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
@Table(name="et_categories")
public class UserCategory {

	@Id
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id")
    private Integer categoryId;
    private Integer userId;
    private String title;
    private String description;
    //private Double totalExpense;

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

   /* public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }*/
}

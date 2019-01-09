package com.griddynamics.Store.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user")
    private Long userId;
    private Date date;
    private double total;
    private String status;

    public Order() {
    }

    public Order(Long userId, Date date, double total, String status) {
        this.userId = userId;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.jinshun.contact.utils.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.TemporalType;
import java.util.Date;

public class BidQueryModel {

    private Long companyId;

    private String name;

    private String area;

    private Integer bigType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date apply_date_min;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date apply_date_max;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deposit_deadline_min;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deposit_deadline_max;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bid_open_time_min;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bid_open_time_max;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getApply_date_min() {
        return apply_date_min;
    }

    public void setApply_date_min(Date apply_date_min) {
        this.apply_date_min = apply_date_min;
    }

    public Date getApply_date_max() {
        return apply_date_max;
    }

    public void setApply_date_max(Date apply_date_max) {
        this.apply_date_max = apply_date_max;
    }

    public Date getDeposit_deadline_min() {
        return deposit_deadline_min;
    }

    public void setDeposit_deadline_min(Date deposit_deadline_min) {
        this.deposit_deadline_min = deposit_deadline_min;
    }

    public Date getDeposit_deadline_max() {
        return deposit_deadline_max;
    }

    public void setDeposit_deadline_max(Date deposit_deadline_max) {
        this.deposit_deadline_max = deposit_deadline_max;
    }

    public Date getBid_open_time_min() {
        return bid_open_time_min;
    }

    public void setBid_open_time_min(Date bid_open_time_min) {
        this.bid_open_time_min = bid_open_time_min;
    }

    public Date getBid_open_time_max() {
        return bid_open_time_max;
    }

    public void setBid_open_time_max(Date bid_open_time_max) {
        this.bid_open_time_max = bid_open_time_max;
    }

    public Integer getBigType() {
        return bigType;
    }

    public void setBigType(Integer bigType) {
        this.bigType = bigType;
    }
}

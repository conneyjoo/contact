package com.jinshun.contact.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    //证书类型
    private String type;

    //专业
    private String major;

    //姓名
    private String name;

    //发证机关
    private String office;

    //发证时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lssueDate;

    //有效期截止日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validDate;

    //挂靠截止日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date relyDate;

    //挂靠费用
    private Double relyPrice;

    //费用支付时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

    //备注
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Date getLssueDate() {
        return lssueDate;
    }

    public void setLssueDate(Date lssueDate) {
        this.lssueDate = lssueDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getRelyDate() {
        return relyDate;
    }

    public void setRelyDate(Date relyDate) {
        this.relyDate = relyDate;
    }

    public Double getRelyPrice() {
        return relyPrice;
    }

    public void setRelyPrice(Double relyPrice) {
        this.relyPrice = relyPrice;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

package com.jinshun.contact.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_success_bid")
public class SuccessBid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    //区域
    private String area;

    //项目类型
    private String type;

    //订立时间
    @Column(name="conclude_time")
    private Date concludeTime;

    //项目名称
    private String name;

    //工期
    @Column(name="time_limit")
    private String timeLimit;

    //合同价
    @Column(name="contact_price")
    private Integer contactPrice;

    //审定价
    @Column(name="judgement_price")
    private Integer judgementPrice;

    //建造师
    private String constructor;

    //分包负责人
    private String principal;

    //管理费及所得税
    @Column(name="management_cost")
    private Integer managementCost;

    //工程保险费
    @Column(name="premium_cost")
    private Integer premiumCost;

    //是否入库（0未入库，1已入库）
    @Column(name="in_warehouse")
    private Integer inWarehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getConcludeTime() {
        return concludeTime;
    }

    public void setConcludeTime(Date concludeTime) {
        this.concludeTime = concludeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getContactPrice() {
        return contactPrice;
    }

    public void setContactPrice(Integer contactPrice) {
        this.contactPrice = contactPrice;
    }

    public Integer getJudgementPrice() {
        return judgementPrice;
    }

    public void setJudgementPrice(Integer judgementPrice) {
        this.judgementPrice = judgementPrice;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Integer getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(Integer managementCost) {
        this.managementCost = managementCost;
    }

    public Integer getPremiumCost() {
        return premiumCost;
    }

    public void setPremiumCost(Integer premiumCost) {
        this.premiumCost = premiumCost;
    }

    public Integer getInWarehouse() {
        return inWarehouse;
    }

    public void setInWarehouse(Integer inWarehouse) {
        this.inWarehouse = inWarehouse;
    }
}

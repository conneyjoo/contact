package com.jinshun.contact.entity;

import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "area")
    private String area;

    //项目类型
    @Column(name = "type")
    private String type;

    //订立时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "conclude_time")
    private Date concludeTime;

    //项目名称
    @Column(name = "name")
    private String name;

    //工期
    @Column(name = "time_limit")
    private String timeLimit;

    //合同价
    @Column(name = "contact_price")
    private Double contactPrice;

    //审定价
    @Column(name = "judgement_price")
    private Double judgementPrice;

    //建造师
    @Column(name = "constructer")
    private String constructer;

    //分包负责人
    @Column(name = "principal")
    private String principal;

    //管理费及所得税
    @Column(name = "management_cost")
    private String managementCost;

    //建造师费
    @Column(name = "builder_cost")
    private String builderCost;

    //工程保险费
    @Column(name = "premium_cost")
    private Double premiumCost;

    //备注
    @Column(name = "remark")
    private String remark;

    //是否入库（0未入库，1已入库）
    @Column(name = "in_warehouse")
    private Integer inWarehouse;

    //订立开始时间(查询条件)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Transient
    private Date concludeTimeBegin;

    //订立结束时间(查询条件)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Transient
    private Date concludeTimeEnd;

    //合同价格范围(查询条件)
    @Transient
    private Integer contactPriceRange;

    //状态（0进行中，1已完成）
    private Integer status;

    //创建者
    private String creator;

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

    public Double getContactPrice() {
        return contactPrice;
    }

    public void setContactPrice(Double contactPrice) {
        this.contactPrice = contactPrice;
    }

    public Double getJudgementPrice() {
        return judgementPrice;
    }

    public void setJudgementPrice(Double judgementPrice) {
        this.judgementPrice = judgementPrice;
    }

    public String getConstructer() {
        return constructer;
    }

    public void setConstructer(String constructer) {
        this.constructer = constructer;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(String managementCost) {
        this.managementCost = managementCost;
    }

    public Double getPremiumCost() {
        return premiumCost;
    }

    public void setPremiumCost(Double premiumCost) {
        this.premiumCost = premiumCost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getInWarehouse() {
        return inWarehouse;
    }

    public void setInWarehouse(Integer inWarehouse) {
        this.inWarehouse = inWarehouse;
    }

    public Date getConcludeTimeBegin() {
        return concludeTimeBegin;
    }

    public void setConcludeTimeBegin(Date concludeTimeBegin) {
        this.concludeTimeBegin = concludeTimeBegin;
    }

    public Date getConcludeTimeEnd() {
        return concludeTimeEnd;
    }

    public void setConcludeTimeEnd(Date concludeTimeEnd) {
        this.concludeTimeEnd = concludeTimeEnd;
    }

    public Integer getContactPriceRange() {
        return contactPriceRange;
    }

    public void setContactPriceRange(Integer contactPriceRange) {
        this.contactPriceRange = contactPriceRange;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBuilderCost() {
        return builderCost;
    }

    public void setBuilderCost(String builderCost) {
        this.builderCost = builderCost;
    }
}

package com.jinshun.contact.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    //区域
    private String area;

    //内部负责人
    private String principal;

    //项目发布时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "pub_date")
    private Date pubDate;

    //项目名称
    private String name;

    //项目类型
    private String type;

    //招标价格
    @Column(name = "bid_price")
    private Double bidPrice;

    //报名时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "apply_date")
    private Date applyDate;

    //建造师
    private String constructor;

    //保证金
    @Column(name = "deposit_price")
    private Double depositPrice;

    //保证金截止日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "deposit_deadline")
    private Date depositDeadline;

    //保证金审批(0未审批，1同意，2否决)
    @Column(name = "deposit_approval")
    private Integer depositApproval;

    //保证金汇出时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "deposit_remit_time")
    private Date depositRemitTime;

    //开标时间
    @Column(name = "bid_open_time")
    private String bidOpenTime;

    //开标结果（0未开标，1中标，2未中标）
    @Column(name = "bid_open_result")
    private Integer bidOpenResult;

    //保证金退回时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "deposit_return_time")
    private Date depositReturnTime;

    //备注
    @Column(name = "remark")
    private String remark;

    //是否入库（0未入库，1已入库）
    @Column(name = "in_warehouse")
    private Integer inWarehouse;

    //借用人保证金汇入时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "borrower_import_time")
    private Date borrowerImportTime;

    //保证金退回借用人时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "borrower_return_time")
    private Date borrowerReturnTime;

    //是否被审批为中标（0未，1是）
    @Column(name = "first_applied")
    private Integer firstApplied;

    //状态（0进行中，1已完成）
    private Integer status;

    @Column(name = "remark2")
    private String remark2;

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    @Column(name = "remark3")
    private String remark3;

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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public Double getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(Double depositPrice) {
        this.depositPrice = depositPrice;
    }

    public Date getDepositDeadline() {
        return depositDeadline;
    }

    public void setDepositDeadline(Date depositDeadline) {
        this.depositDeadline = depositDeadline;
    }

    public Integer getDepositApproval() {
        return depositApproval;
    }

    public void setDepositApproval(Integer depositApproval) {
        this.depositApproval = depositApproval;
    }

    public Date getDepositRemitTime() {
        return depositRemitTime;
    }

    public void setDepositRemitTime(Date depositRemitTime) {
        this.depositRemitTime = depositRemitTime;
    }

    public String getBidOpenTime() {
        return bidOpenTime;
    }

    public void setBidOpenTime(String bidOpenTime) {
        this.bidOpenTime = bidOpenTime;
    }

    public Integer getBidOpenResult() {
        return bidOpenResult;
    }

    public void setBidOpenResult(Integer bidOpenResult) {
        this.bidOpenResult = bidOpenResult;
    }

    public Date getDepositReturnTime() {
        return depositReturnTime;
    }

    public void setDepositReturnTime(Date depositReturnTime) {
        this.depositReturnTime = depositReturnTime;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Date getBorrowerImportTime() {
        return borrowerImportTime;
    }

    public void setBorrowerImportTime(Date borrowerImportTime) {
        this.borrowerImportTime = borrowerImportTime;
    }

    public Date getBorrowerReturnTime() {
        return borrowerReturnTime;
    }

    public void setBorrowerReturnTime(Date borrowerReturnTime) {
        this.borrowerReturnTime = borrowerReturnTime;
    }

    public Integer getFirstApplied() {
        return firstApplied;
    }

    public void setFirstApplied(Integer firstApplied) {
        this.firstApplied = firstApplied;
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
}

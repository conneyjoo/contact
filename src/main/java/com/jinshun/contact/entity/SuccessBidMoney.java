package com.jinshun.contact.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_success_bid_money")
public class SuccessBidMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //所属中标记录
    @ManyToOne
    @JoinColumn(name = "success_bid_id")
    private SuccessBid successBid;

    //利润
    @Column(name="profit")
    private Integer profit;

    //开票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="ticket_open_time")
    private Date ticketOpenTime;

    //开票不含税额
    @Column(name="ticket_open_nin_tax")
    private Integer ticketOpenNinTax;

    //开票增值税
    @Column(name="ticket_open_tax")
    private Integer ticketOpenTax;

    //开票总额
    @Column(name="ticket_open_amount")
    private Integer ticketOpenAmount;

    //收款额日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="collected_amount_time")
    private Date collectedAmountTime;

    //收款额
    @Column(name="collected_amount")
    private Integer collectedAmount;

    //收款额备注
    @Column(name="collected_amount_remark")
    private String collectedAmountRemark;

    //付款额日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="payment_amount_time")
    private Date paymentAmountTime;

    //付款额
    @Column(name="payment_amount")
    private Integer paymentAmount;

    //付款额备注
    @Column(name="payment_amount_remark")
    private String paymentAmountRemark;

    //收增值税专用发票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="tax_bill_time")
    private Date taxBillTime;

    //收增值税专用发票不含税额
    @Column(name="tax_bill_nin_tax")
    private Integer taxBillNinTax;

    //收增值税专用发票增值税
    @Column(name="tax_bill_tax")
    private Integer taxBillTax;

    //收增值税专用发票合计
    @Column(name="tax_bill_amount")
    private Integer taxBillAmount;

    //收增值税专用发票备注
    @Column(name="tax_bill_remark")
    private String taxBillRemark;

    //收普通发票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="normal_bill_time")
    private Date normalBillTime;

    //收普通发票金额
    @Column(name="normal_bill_amount")
    private Integer normalBillAmount;

    //收普通发票备注
    @Column(name="normal_bill_remark")
    private String normalBillRemark;

    //收工资单日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="payroll_time")
    private Date payrollTime;

    //收工资单金额
    @Column(name="payroll_amount")
    private Integer payrollAmount;

    //备注
    @Column(name="remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SuccessBid getSuccessBid() {
        return successBid;
    }

    public void setSuccessBid(SuccessBid successBid) {
        this.successBid = successBid;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Date getTicketOpenTime() {
        return ticketOpenTime;
    }

    public void setTicketOpenTime(Date ticketOpenTime) {
        this.ticketOpenTime = ticketOpenTime;
    }

    public Integer getTicketOpenNinTax() {
        return ticketOpenNinTax;
    }

    public void setTicketOpenNinTax(Integer ticketOpenNinTax) {
        this.ticketOpenNinTax = ticketOpenNinTax;
    }

    public Integer getTicketOpenTax() {
        return ticketOpenTax;
    }

    public void setTicketOpenTax(Integer ticketOpenTax) {
        this.ticketOpenTax = ticketOpenTax;
    }

    public Integer getTicketOpenAmount() {
        return ticketOpenAmount;
    }

    public void setTicketOpenAmount(Integer ticketOpenAmount) {
        this.ticketOpenAmount = ticketOpenAmount;
    }

    public Date getCollectedAmountTime() {
        return collectedAmountTime;
    }

    public void setCollectedAmountTime(Date collectedAmountTime) {
        this.collectedAmountTime = collectedAmountTime;
    }

    public Integer getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Integer collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public String getCollectedAmountRemark() {
        return collectedAmountRemark;
    }

    public void setCollectedAmountRemark(String collectedAmountRemark) {
        this.collectedAmountRemark = collectedAmountRemark;
    }

    public Date getPaymentAmountTime() {
        return paymentAmountTime;
    }

    public void setPaymentAmountTime(Date paymentAmountTime) {
        this.paymentAmountTime = paymentAmountTime;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentAmountRemark() {
        return paymentAmountRemark;
    }

    public void setPaymentAmountRemark(String paymentAmountRemark) {
        this.paymentAmountRemark = paymentAmountRemark;
    }

    public Date getTaxBillTime() {
        return taxBillTime;
    }

    public void setTaxBillTime(Date taxBillTime) {
        this.taxBillTime = taxBillTime;
    }

    public Integer getTaxBillNinTax() {
        return taxBillNinTax;
    }

    public void setTaxBillNinTax(Integer taxBillNinTax) {
        this.taxBillNinTax = taxBillNinTax;
    }

    public Integer getTaxBillTax() {
        return taxBillTax;
    }

    public void setTaxBillTax(Integer taxBillTax) {
        this.taxBillTax = taxBillTax;
    }

    public Integer getTaxBillAmount() {
        return taxBillAmount;
    }

    public void setTaxBillAmount(Integer taxBillAmount) {
        this.taxBillAmount = taxBillAmount;
    }

    public String getTaxBillRemark() {
        return taxBillRemark;
    }

    public void setTaxBillRemark(String taxBillRemark) {
        this.taxBillRemark = taxBillRemark;
    }

    public Date getNormalBillTime() {
        return normalBillTime;
    }

    public void setNormalBillTime(Date normalBillTime) {
        this.normalBillTime = normalBillTime;
    }

    public Integer getNormalBillAmount() {
        return normalBillAmount;
    }

    public void setNormalBillAmount(Integer normalBillAmount) {
        this.normalBillAmount = normalBillAmount;
    }

    public String getNormalBillRemark() {
        return normalBillRemark;
    }

    public void setNormalBillRemark(String normalBillRemark) {
        this.normalBillRemark = normalBillRemark;
    }

    public Date getPayrollTime() {
        return payrollTime;
    }

    public void setPayrollTime(Date payrollTime) {
        this.payrollTime = payrollTime;
    }

    public Integer getPayrollAmount() {
        return payrollAmount;
    }

    public void setPayrollAmount(Integer payrollAmount) {
        this.payrollAmount = payrollAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}

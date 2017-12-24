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
    private Double profit;

    //开票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="ticket_open_time")
    private Date ticketOpenTime;

    //开票不含税额
    @Column(name="ticket_open_nin_tax")
    private Double ticketOpenNinTax;

    //开票增值税
    @Column(name="ticket_open_tax")
    private Double ticketOpenTax;

    //开票总额
    @Column(name="ticket_open_amount")
    private Double ticketOpenAmount;

    //收款额日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="collected_amount_time")
    private Date collectedAmountTime;

    //收款额
    @Column(name="collected_amount")
    private Double collectedAmount;

    //收款额备注
    @Column(name="collected_amount_remark")
    private String collectedAmountRemark;

    //付款额日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="payment_amount_time")
    private Date paymentAmountTime;

    //付款额
    @Column(name="payment_amount")
    private Double paymentAmount;

    //付款额备注
    @Column(name="payment_amount_remark")
    private String paymentAmountRemark;

    //收增值税专用发票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="tax_bill_time")
    private Date taxBillTime;

    //收增值税专用发票不含税额
    @Column(name="tax_bill_nin_tax")
    private Double taxBillNinTax;

    //收增值税专用发票增值税
    @Column(name="tax_bill_tax")
    private Double taxBillTax;

    //收增值税专用发票合计
    @Column(name="tax_bill_amount")
    private Double taxBillAmount;

    //收增值税专用发票备注
    @Column(name="tax_bill_remark")
    private String taxBillRemark;

    //收普通发票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="normal_bill_time")
    private Date normalBillTime;

    //收普通发票金额
    @Column(name="normal_bill_amount")
    private Double normalBillAmount;

    //收普通发票备注
    @Column(name="normal_bill_remark")
    private String normalBillRemark;

    //收工资单日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="payroll_time")
    private Date payrollTime;

    //收工资单金额
    @Column(name="payroll_amount")
    private Double payrollAmount;

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

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getTicketOpenTime() {
        return ticketOpenTime;
    }

    public void setTicketOpenTime(Date ticketOpenTime) {
        this.ticketOpenTime = ticketOpenTime;
    }

    public Double getTicketOpenNinTax() {
        return ticketOpenNinTax;
    }

    public void setTicketOpenNinTax(Double ticketOpenNinTax) {
        this.ticketOpenNinTax = ticketOpenNinTax;
    }

    public Double getTicketOpenTax() {
        return ticketOpenTax;
    }

    public void setTicketOpenTax(Double ticketOpenTax) {
        this.ticketOpenTax = ticketOpenTax;
    }

    public Double getTicketOpenAmount() {
        return ticketOpenAmount;
    }

    public void setTicketOpenAmount(Double ticketOpenAmount) {
        this.ticketOpenAmount = ticketOpenAmount;
    }

    public Date getCollectedAmountTime() {
        return collectedAmountTime;
    }

    public void setCollectedAmountTime(Date collectedAmountTime) {
        this.collectedAmountTime = collectedAmountTime;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
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

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
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

    public Double getTaxBillNinTax() {
        return taxBillNinTax;
    }

    public void setTaxBillNinTax(Double taxBillNinTax) {
        this.taxBillNinTax = taxBillNinTax;
    }

    public Double getTaxBillTax() {
        return taxBillTax;
    }

    public void setTaxBillTax(Double taxBillTax) {
        this.taxBillTax = taxBillTax;
    }

    public Double getTaxBillAmount() {
        return taxBillAmount;
    }

    public void setTaxBillAmount(Double taxBillAmount) {
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

    public Double getNormalBillAmount() {
        return normalBillAmount;
    }

    public void setNormalBillAmount(Double normalBillAmount) {
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

    public Double getPayrollAmount() {
        return payrollAmount;
    }

    public void setPayrollAmount(Double payrollAmount) {
        this.payrollAmount = payrollAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}

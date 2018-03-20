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

    //开票日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="ticket_open_time")
    private Date ticketOpenTime;

    //开票金额
    @Column(name="ticket_open_amount")
    private Double ticketOpenAmount;

    //收款日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="collected_amount_time")
    private Date collectedAmountTime;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    //付款日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="pay_time")
    private Date payTime;

    //收款额
    @Column(name="collected_amount")
    private Double collectedAmount;

    //扣：管理费用
    @Column(name="management_cost")
    private Double managementCost;

    //扣：税金
    @Column(name="income_tax")
    private Double incomeTax;

    //退还: 外地已交税金
    @Column(name="added_tax")
    private Double addedTax;

    //扣：城建及教费
    @Column(name="construction_cost")
    private Double constructionCost;

    //扣：城建个所税
    @Column(name="construction_income_tax")
    private Double constructionIncomeTax;

    //扣：印花税
    @Column(name="printing_tax")
    private Double printingTax;

    //扣：暂扣做账发票
    @Column(name="account_invoice")
    private Double accountInvoice;

    //扣：保险费
    @Column(name="insurance_premium")
    private Double insurancePremium;

    //退还：抵扣增值税
    @Column(name="deductible_tax")
    private Double deductibleTax;

    //退还：已交增值税
    @Column(name="paied_tax")
    private Double paiedTax;

    //暂付款
    @Column(name="temporary_payment")
    private Double temporaryPayment;

    //应付项目工程款
    @Column(name="project_payment")
    private Double projectPayment;

    //应收材料发票
    @Column(name="receivable_invoice")
    private Double receivableInvoice;

    //已收材料发票
    @Column(name="receipt_invoice")
    private Double receiptInvoice;

    //尚缺材料发票
    @Column(name="lack_invoice")
    private Double lackInvoice;

    //多余的扣增值税
    @Column(name="surplus_tax")
    private Double surplusTax;

    //录入人
    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

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

    public Date getTicketOpenTime() {
        return ticketOpenTime;
    }

    public void setTicketOpenTime(Date ticketOpenTime) {
        this.ticketOpenTime = ticketOpenTime;
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

    public Double getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(Double managementCost) {
        this.managementCost = managementCost;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getAddedTax() {
        return addedTax;
    }

    public void setAddedTax(Double addedTax) {
        this.addedTax = addedTax;
    }

    public Double getConstructionCost() {
        return constructionCost;
    }

    public void setConstructionCost(Double constructionCost) {
        this.constructionCost = constructionCost;
    }

    public Double getConstructionCostIncomeTax() {
        return constructionIncomeTax;
    }

    public void setConstructionCostIncomeTax(Double constructionIncomeTax) {
        this.constructionIncomeTax = constructionIncomeTax;
    }

    public Double getPrintingTax() {
        return printingTax;
    }

    public void setPrintingTax(Double printingTax) {
        this.printingTax = printingTax;
    }

    public Double getAccountInvoice() {
        return accountInvoice;
    }

    public void setAccountInvoice(Double accountInvoice) {
        this.accountInvoice = accountInvoice;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    public Double getDeductibleTax() {
        return deductibleTax;
    }

    public void setDeductibleTax(Double deductibleTax) {
        this.deductibleTax = deductibleTax;
    }

    public Double getPaiedTax() {
        return paiedTax;
    }

    public void setPaiedTax(Double paiedTax) {
        this.paiedTax = paiedTax;
    }

    public Double getTemporaryPayment() {
        return temporaryPayment;
    }

    public void setTemporaryPayment(Double temporaryPayment) {
        this.temporaryPayment = temporaryPayment;
    }

    public Double getProjectPayment() {
        return projectPayment;
    }

    public void setProjectPayment(Double projectPayment) {
        this.projectPayment = projectPayment;
    }

    public Double getReceivableInvoice() {
        return receivableInvoice;
    }

    public void setReceivableInvoice(Double receivableInvoice) {
        this.receivableInvoice = receivableInvoice;
    }

    public Double getReceiptInvoice() {
        return receiptInvoice;
    }

    public void setReceiptInvoice(Double receiptInvoice) {
        this.receiptInvoice = receiptInvoice;
    }

    public Double getLackInvoice() {
        return lackInvoice;
    }

    public void setLackInvoice(Double lackInvoice) {
        this.lackInvoice = lackInvoice;
    }

    public Double getSurplusTax() {
        return surplusTax;
    }

    public void setSurplusTax(Double surplusTax) {
        this.surplusTax = surplusTax;
    }

    public Double getConstructionIncomeTax() {
        return constructionIncomeTax;
    }

    public void setConstructionIncomeTax(Double constructionIncomeTax) {
        this.constructionIncomeTax = constructionIncomeTax;
    }
}

package com.jinshun.contact.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_file")
public class File {

    public static final String FILE_TYPE_BID = "BID";

    public static final String FILE_TYPE_SUCCESS_BID = "SUCCESS_BID";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "bid_id")
    private Long bidId;

    @Column(name = "success_bid_id")
    private Long successBidId;

    /** 1:招标文件, 2:中标通知书, 3:工程合同, 4:分包合同, 5:工程保险单, 6:合同验收证书, 7:牢记报告, 8:其他, 9:银行进出凭证, 10:工程发票, 11:抵扣发票, 12:成本发票, 13:工资单, 14:财务结算单，15：其他 */
    @Column(name = "business_type")
    private Integer businessType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getSuccessBidId() {
        return successBidId;
    }

    public void setSuccessBidId(Long successBidId) {
        this.successBidId = successBidId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}

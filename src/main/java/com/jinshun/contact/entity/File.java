package com.jinshun.contact.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_file")
public class File {

    public static final String FILE_TYPE_BID = "BID";

    public static final String FILE_TYPE_BID_SUCCESS = "BID_SUCCESS";

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

    @Column(name = "bid_success_id")
    private Long bidSuccessId;

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

    public Long getBidSuccessId() {
        return bidSuccessId;
    }

    public void setBidSuccessId(Long bidSuccessId) {
        this.bidSuccessId = bidSuccessId;
    }
}

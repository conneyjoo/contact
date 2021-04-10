package com.jinshun.contact.utils.model;

public class DocumentQueryModel {

    private Long companyId;

    //文件名称
    private String name;

    //发文机关
    private String office;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

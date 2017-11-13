package com.wisn.realm.bean;

import io.realm.RealmObject;

/**
 * Created by wisn on 2017/11/10.
 */

public class Company extends RealmObject {
    private int companyid;
    private String company_Name;

    public Company() {
    }

    public Company(int companyid, String company_Name) {
        this.companyid = companyid;
        this.company_Name = company_Name;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    @Override
    public String toString() {
        return "Company{" +
               "companyid=" + companyid +
               ", company_Name='" + company_Name + '\'' +
               '}';
    }
}

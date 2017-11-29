package com.wisn.realm.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wisn on 2017/11/10.
 */

public class Employee extends RealmObject {
    @PrimaryKey
    private int EmployeeId;
    private String employee_Name;
    //一对多
    public Company company;

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployee_Name() {
        return employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        this.employee_Name = employee_Name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
               "EmployeeId=" + EmployeeId +
               ", employee_Name='" + employee_Name + '\'' +
               ", company=" + company +
               '}';
    }
}

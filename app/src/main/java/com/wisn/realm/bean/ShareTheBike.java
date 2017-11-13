package com.wisn.realm.bean;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by wisn on 2017/11/10.
 */

public class ShareTheBike extends RealmObject{
    private int bick_id;
    private String bick_Name;
    private RealmList<Employee>  employees;

    public String getBick_Name() {
        return bick_Name;
    }

    public void setBick_Name(String bick_Name) {
        this.bick_Name = bick_Name;
    }

    public RealmList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(RealmList<Employee> employees) {
        this.employees = employees;
    }

    public int getBick_id() {
        return bick_id;
    }

    public void setBick_id(int bick_id) {
        this.bick_id = bick_id;
    }

    @Override
    public String toString() {
        return "ShareTheBike{" +
               "bick_id=" + bick_id +
               ", bick_Name='" + bick_Name + '\'' +
               ", employees=" + employees +
               '}';
    }
}

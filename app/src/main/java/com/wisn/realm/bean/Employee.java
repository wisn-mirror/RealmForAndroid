package com.wisn.realm.bean;

import io.realm.RealmObject;

/**
 * Created by wisn on 2017/11/10.
 */

public class Employee extends RealmObject {
    private int EmployeeId;
    //一对多
    private Company company;


}

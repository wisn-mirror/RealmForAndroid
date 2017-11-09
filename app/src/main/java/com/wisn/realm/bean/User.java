package com.wisn.realm.bean;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by wisn on 2017/11/9.
 */
@RealmClass
public class User extends RealmObject {

    private byte bytetest;
    private short shorttest;
    @PrimaryKey
    private int inttest;
    private long longtest;
    private float floattest;
    private double doubletest;
    private String Stringtest;
    private Date datetest;

    public byte getBytetest() {
        return bytetest;
    }

    public void setBytetest(byte bytetest) {
        this.bytetest = bytetest;
    }

    public short getShorttest() {
        return shorttest;
    }

    public void setShorttest(short shorttest) {
        this.shorttest = shorttest;
    }

    public int getInttest() {
        return inttest;
    }

    public void setInttest(int inttest) {
        this.inttest = inttest;
    }

    public long getLongtest() {
        return longtest;
    }

    public void setLongtest(long longtest) {
        this.longtest = longtest;
    }

    public float getFloattest() {
        return floattest;
    }

    public void setFloattest(float floattest) {
        this.floattest = floattest;
    }

    public double getDoubletest() {
        return doubletest;
    }

    public void setDoubletest(double doubletest) {
        this.doubletest = doubletest;
    }

    public String getStringtest() {
        return Stringtest;
    }

    public void setStringtest(String stringtest) {
        Stringtest = stringtest;
    }

    public Date getDatetest() {
        return datetest;
    }

    public void setDatetest(Date datetest) {
        this.datetest = datetest;
    }

    @Override
    public String toString() {
        return "People{" +
               "bytetest=" + bytetest +
               ", shorttest=" + shorttest +
               ", inttest=" + inttest +
               ", longtest=" + longtest +
               ", floattest=" + floattest +
               ", doubletest=" + doubletest +
               ", Stringtest='" + Stringtest + '\'' +
               ", datetest=" + datetest +
               '}';
    }
}

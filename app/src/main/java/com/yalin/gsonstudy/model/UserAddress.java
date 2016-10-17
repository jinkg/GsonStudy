package com.yalin.gsonstudy.model;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class UserAddress {
    public String street;
    public String houseNumber;
    public String city;
    public String country;

    @Override
    public String toString() {
        return "UserAddress{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

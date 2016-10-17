package com.yalin.gsonstudy.model;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class Owner {
    public String name;
    public UserAddress address;

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

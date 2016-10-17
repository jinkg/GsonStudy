package com.yalin.gsonstudy.model;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class NestedUserSimple {
    public String name;
    public String email;
    public int age;
    public boolean isDeveloper;

    public UserAddress userAddress;

    @Override
    public String toString() {
        return "NestedUserSimple{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                ", userAddress=" + userAddress +
                '}';
    }
}

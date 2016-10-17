package com.yalin.gsonstudy.model;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class UserSimple {
    public String name;
    public String email;
    public int age;
    public boolean isDeveloper;

    @Override
    public String toString() {
        return "UserSimple{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                '}';
    }
}



package com.yalin.gsonstudy.model;

/**
 * 作者：YaLin
 * 日期：2016/10/17.
 */

public class Restaurant {
    public String name;

    public Owner owner;
    public Staff cook;
    public Staff waiter;

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", cook=" + cook +
                ", waiter=" + waiter +
                '}';
    }
}

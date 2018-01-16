package com.barisatalay.sample;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class mdlPerson extends BasePerson{
    private int age;
    private String address;

    public mdlPerson(String code, String name) {
        super(code, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

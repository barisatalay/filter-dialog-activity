package com.barisatalay.sample;

/**
 * Created by mceviktekin on 16.01.2018.
 */

public class BasePerson {
    private String code;
    private String name;

    public BasePerson(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

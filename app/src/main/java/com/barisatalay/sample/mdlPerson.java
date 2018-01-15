package com.barisatalay.sample;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class mdlPerson {
    private String code;
    private String name;

    public mdlPerson(String code, String name) {
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

package com.barisatalay.filterdialog.model;

/**
 * Created by Barış ATALAY on 12.01.2018.
 */

public class FilterItem {
    private String code;
    private String name;

    private FilterItem(Builder builder) {
        setCode(builder.code);
        setName(builder.name);
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

    public static final class Builder {
        private String code;
        private String name;

        public Builder() {
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public FilterItem build() {
            return new FilterItem(this);
        }
    }
}

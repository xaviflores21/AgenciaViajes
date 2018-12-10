package com.example.xavi.proyecto4.httpclient;

/**
 * Created by joseco on 11/17/2015.
 */
public enum MethodType {

    POST("POST");

    private final String value;

    private MethodType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}

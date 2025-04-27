package com.muaun.trackme.util;

public class LogUI {

    private final String text;

    public LogUI(String text) {
        this.text = text;
    }

    public String getError() {
        return "\u001B[31m" + text + "\u001B[0m";
    }

    public String getNormal() {
        return "\u001B[32m" + text + "\u001B[0m";
    }
}

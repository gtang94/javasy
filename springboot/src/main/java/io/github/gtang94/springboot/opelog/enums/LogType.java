package io.github.gtang94.springboot.opelog.enums;

public enum LogType {

    LOGIN("login"),
    LOGOUT("logout"),
    OPERATE("operate");

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LogType(String message) {
        this.message = message;
    }
}

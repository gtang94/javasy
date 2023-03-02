package io.github.gtang94.springboot.opelog.enums;


public enum LogStatus {
    SUCCESS("success"),
    FAIL("fail");

    String message;

    LogStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

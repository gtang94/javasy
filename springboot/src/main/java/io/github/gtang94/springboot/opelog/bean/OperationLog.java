package io.github.gtang94.springboot.opelog.bean;

public class OperationLog {

    private Long operationLog;

    private String logType;

    private String logName;

    private Long userId;

    private String className;

    private String method;

    private String message;

    private String succeed;

    public Long getOperationLog() {
        return operationLog;
    }

    public void setOperationLog(Long operationLog) {
        this.operationLog = operationLog;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }
}

package io.github.gtang94.springboot.opelog.factory;

import io.github.gtang94.springboot.opelog.annotation.OperateLog;
import io.github.gtang94.springboot.opelog.bean.OperationLog;
import io.github.gtang94.springboot.opelog.enums.LogType;

public class LogFactory {

    public static OperationLog createOperateLog(LogType logType, Long userId, String operateName, String className, String method, String msg, String logStatus) {
        OperationLog operationLog = new OperationLog();
        return operationLog;
    }

}

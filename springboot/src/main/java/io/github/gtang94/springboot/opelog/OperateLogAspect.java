package io.github.gtang94.springboot.opelog;

import io.github.gtang94.springboot.opelog.annotation.OperateLog;
import io.github.gtang94.springboot.opelog.factory.LogTaskFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class OperateLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(OperateLogAspect.class);

    @Pointcut(value = "@annotation(io.github.gtang94.springboot.opelog.annotation.OperateLog)")
    public void logCut() {}

    @Around("logCut()")
    public Object logWrite(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        try {
            logHandle(point);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private void logHandle(ProceedingJoinPoint point) throws Exception {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("illegal argument exception");
        }

        methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        String methodName = currentMethod.getName();

        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        OperateLog annotation = currentMethod.getAnnotation(OperateLog.class);
        String operateName = annotation.value();
        String key = annotation.key();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(params);
            sb.append(" & ");
        }

        if (operateName.contains("修改") || operateName.contains("编辑")) {
        }

        LogManager.manager().execute(
                LogTaskFactory.operateLog(null, "operationName", className, methodName, "msg")
        );
    }
}

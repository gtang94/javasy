package io.github.gtang94.springboot.aop.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanggq
 * @class WebLogAspect
 * @description
 * @date 2021/4/22
 **/
@Aspect
@Component
@Order(100)
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    /**
     * @description: 定义切入点为 controller下的所有类
     * @return: null
     * @author: tanggq
     * @date: 2021/4/22
     **/
    @Pointcut("execution(public * io.github.gtang94.springboot.aop.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * @description: 接收请求，并记录数据
     * @return: null
     * @author: tanggq
     * @date: 2021/4/22
     **/
    @Before(value = "webLog()&&@annotation(controllerWebLog)")
    public void doBefore(JoinPoint joinPoint, ControllerWebLog controllerWebLog) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        Map<String, Object> threadInfo = new HashMap<>();
        logger.info("Url: " + request.getRequestURL());
        threadInfo.put("url", request.getRequestURL());
        threadLocal.set(threadInfo);
    }

    /**
     * @description: 执行成功后处理
     * @return: null
     * @author: tanggq
     * @date: 2021/4/22
     **/
    @AfterReturning(value = "webLog() && @annotation(controllerWebLog)", returning = "ret")
    public void doAfterReturning(ControllerWebLog controllerWebLog, Object ret) throws Throwable {
        Map<String, Object> threadInfo = threadLocal.get();
        threadInfo.put("result", ret);
        if (controllerWebLog.intoDB()) {
            // insert into db
        }

        logger.info("response: " + ret);
    }

    /**
     * @description: 获取执行时间
     * @return: null
     * @author: tanggq
     * @date: 2021/4/22
     **/
    @Around(value = "webLog())")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = proceedingJoinPoint.proceed();
        Map<String, Object> threadInfo = threadLocal.get();
        Long takeTime = System.currentTimeMillis() - startTime;
        threadInfo.put("takeTime", takeTime);
        logger.info("耗时: ", takeTime);
        threadLocal.set(threadInfo);

        // Object ob = "2333333"; // 此处可以直接拦截请求，不让执行目标方法
        return ob;
    }

    /**
     * @description: 异常处理
     * @return: null
     * @author: tanggq
     * @date: 2021/4/22
     **/
    @AfterThrowing(value = "webLog()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        RequestAttributes ra =  RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        logger.error("{}接口异常{}", request.getRequestURL(), throwable);
    }

}

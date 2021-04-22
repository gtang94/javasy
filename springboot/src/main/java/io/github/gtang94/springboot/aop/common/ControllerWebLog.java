package io.github.gtang94.springboot.aop.common;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControllerWebLog {
    String name();
    boolean intoDB() default false;
}

package io.github.gtang94.springboot.opelog.annotation;


import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperateLog {

    String value() default "";

    String key() default "";
}

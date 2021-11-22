package io.github.gtang94.springboot.mybatis.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author tanggq
 * @class Person
 * @description
 * @date 2021/5/31
 **/
public class Person {
    private String name;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @JsonFormat( pattern = "yyyy-MM-dd", timezone = "GMT+8" )
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

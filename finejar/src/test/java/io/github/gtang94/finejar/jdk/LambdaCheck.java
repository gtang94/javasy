package io.github.gtang94.finejar.jdk;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tanggq
 * @class LambdaCheck
 * @description 通过Lambda表达式校验
 * @date 2021/4/21
 **/
public class LambdaCheck {

    /**
     * @description: 判断List集合中对象的属性是否为空
     * @return: null
     * @author: tanggq
     * @date: 2021/4/21
     **/
    @Test
    public void checkNullArray() {

        List<Student> students1 = Lists.newArrayList(
                new Student("zhangsan", 12),
                new Student("lisi", 22)
        );
        List<Student> notNullName1 = students1.stream()
                .filter(s -> s!=null)
                .filter(s -> !Strings.isNullOrEmpty(s.getName()))
                .collect(Collectors.toList());
        System.out.println(notNullName1.toString());


        List<Student> students2 = Lists.newArrayList();
        List<Student> notNullName2 = students2.stream()
                .filter(s -> s!=null)
                .filter(s -> !Strings.isNullOrEmpty(s.getName()))
                .collect(Collectors.toList());
        System.out.println(notNullName2.toString());

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Student {
        private String name;
        private Integer age;

        public Student(String name) {
            this.name = name;
        }
    }
}

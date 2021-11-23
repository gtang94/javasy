package io.github.gtang94.finejar.jdk;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @author tanggq
 * @class OptionalOperation
 * @description
 * @date 2021/6/21
 **/
public class OptionalOperation {

    /**
     * @description 对象为空判断，相当于if(){...}
     * @return: null
     * @author tanggq
     * @date 2021/6/21
     **/
    @Test
    public void objectNullCheck() {
        String str = null;

        Optional.ofNullable(str).ifPresent(
                newStr -> {
                    System.err.println(newStr);
                }
        );
    }

    public void objectNullCheck2() {
        String str = null;
    }

    @Test
    public void test() {

        List<Stu> stuList = Lists.newArrayList();
        stuList.add(new Stu("zhangsan", 12));
        stuList.add(new Stu("lisi", 18));
        stuList.add(new Stu("wangwu", 9));

        long count = stuList.stream().filter(var1 -> var1.getAge() > 10).mapToInt(Stu::getAge).sum();
        System.err.println(count);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Stu {
        String name;
        int age;
    }

    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        System.err.println(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00").format(localDateTime));
        System.err.println(DateTimeFormatter.ofPattern("yyyy-MM-dd 59:59:59").format(localDateTime));
    }
}

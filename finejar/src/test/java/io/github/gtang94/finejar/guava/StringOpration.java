package io.github.gtang94.finejar.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author tanggq
 * @class StringOpration
 * @description 字符串操作
 * @date 2021/4/20
 **/
public class StringOpration {

    /**
     * @description: 字符串的拼接、null字符串的拼接
     * @return: null
     * @throws:
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void stringSplicing() {
        ArrayList<String> list = Lists.newArrayList("a", "b", null, "c");
        String join1 = Joiner.on(",").skipNulls().join(list);
        System.out.println(join1);

        String join2 = Joiner.on(",").useForNull("空值").join("a", "b", null, "c");
        System.out.println(join2);
    }

    /**
     * @description: 分割字符串，分割空字符串
     * @return: null
     * @throws:
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void stringSegmentation() {
        String str = ",,,a,b,,,c";

        String[] splitArr = str.split(",");
        System.out.println("====== JDK =======");
        Arrays.stream(splitArr).forEach(System.out::println);

        System.out.println("====== Guava =======");
        Iterable<String> splitStr = Splitter.on(",")
                .omitEmptyStrings() // 忽略空值
                .trimResults() //过滤结果中的空白值
                .split(str);
        splitStr.forEach(System.out::println);


    }
}

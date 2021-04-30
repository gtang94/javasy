package io.github.gtang94.finejar.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author tanggq
 * @class StringOpration
 * @description 字符串操作
 * @date 2021/4/20
 **/
public class StringOperation {

    /**
     * @description: 字符串的拼接、null字符串的拼接
     * @return: null
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

    /**
     * @description 字符串为空校验
     * @return: null
     * @author tanggq
     * @date 2021/4/30
     **/
    @Test
    public void stringNullOrEmpty() {
        String str1 = "";
        String str2 = null;
        String str3 = "tanggq";

        System.err.println( Strings.isNullOrEmpty(str1) );
        System.err.println( Strings.isNullOrEmpty(str2) );
        System.err.println( Strings.isNullOrEmpty(str3) );
    }

    /**
     * @description 获取两个String的公共前缀 或 公共后缀（即 前后相同的部分）
     * @return: null
     * @author tanggq
     * @date 2020/4/30
     **/
    @Test
    public void stringCommonPrefixOrSuffix() {
        String str0 = "AAA-ccc-999-B525";
        String str1 = "AA9-ccc-999-C525";

        System.err.println( Strings.commonPrefix(str0, str1) );
        System.err.println( Strings.commonSuffix(str0, str1) );
    }

    @Test
    public void test() {
        String str1 = "dsaklfjqw";
        String str2 = "kljklasfn";

        System.err.println(Strings.commonPrefix("OOOccSSS", "OccBBB"));
    }


}

package io.github.gtang94.finejar.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author tanggq
 * @class DataVerification
 * @description 数据校验测试类
 * @date 2021/4/20
 **/
public class DataVerification {

    /**
     * @description: 非空判断
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void checkNull() {
        String params1 = "token";
        String name1 = Preconditions.checkNotNull(params1, "params1 参数为空");
        System.out.println(name1);

        String params2 = null;
        String name2 = Preconditions.checkNotNull(params2, "params2 参数为空");
        System.out.println(name2);
    }

    /**
     * @description: 预期值判断
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void checkArgument() {
        String param1 = "www.baidu.com";
        String param2 = "www.gogle.com";

        Preconditions.checkArgument(param1.equals(param2), "[%s] 和 [%s] 两个参数不相同", param1, param2);
    }

    /**
     * @description: 数组/集合索引越界校验
     * @return: null
     * @author: tanggq
     * @date: 2021/4/20
     **/
    @Test
    public void checkElement() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        int index = Preconditions.checkElementIndex(7, list.size());
        System.out.println(index);
    }

}

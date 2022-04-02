package io.github.gtang94.finejar.jdk.jvm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author tanggq
 * @class GC
 * @description
 * @date 2022/3/31
 **/
public class GC {

    @Test
    public void test() {
        int i = 0;
        while (i < 10000 * 10000) {
            HashMap map = Maps.newHashMap();
            List list = Lists.newArrayList(i, i * 10, i * 100);
//            System.err.println(list.toString());
            i++;
        }
    }
}

package io.github.gtang94.finejar.jdk;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.err.println(entry.getValue());
        }

    }
}

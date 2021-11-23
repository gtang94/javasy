package io.github.gtang94.finejar.jdk;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tanggq
 * @class MapSort
 * @description Map流相关
 * @date 2021/4/25
 **/
public class MapStream {

    /**
     * @description HashMap根据value排序
     * @return: null
     * @author tanggq
     * @date 2021/4/25
     **/
    @Test
    public void hashMapSortByValue() {
        HashMap<String, String> hashMap = Maps.newHashMap();
        // ID, 优先顺序
        hashMap.put("2", "2");
        hashMap.put("3", "3");
        hashMap.put("4", "4");
        hashMap.put("1", "6");
        hashMap.put("5", "1");
        hashMap.put("6", "7");

        Map<String, String> sortedMap = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
        System.err.println(sortedMap.toString());
    }

    /**
     * @description HashMap根据key排序
     * @return: null
     * @author tanggq
     * @date 2021/4/25
     **/
    @Test
    public void hashMapSortByKey() {
        HashMap<String, String> hashMap = Maps.newHashMap();
        // ID, 优先顺序
        hashMap.put("2", "2");
        hashMap.put("3", "3");
        hashMap.put("4", "4");
        hashMap.put("1", "6");
        hashMap.put("5", "1");
        hashMap.put("6", "7");

        Map<String, String> sortedMap = hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
        System.err.println(sortedMap.toString());
    }

    /**
     * @description HashMap根据指定的字符串排序
     * @author tanggq
     * @date 2021/7/6
     **/
    @Test
    public void treeMapSortByOtherMap() {
        HashMap<String, List<Integer>> hashMap = Maps.newHashMap();
        hashMap.put("test1", Lists.newArrayList(1, 11, 111));
        hashMap.put("test2", Lists.newArrayList(2, 22, 222));
        hashMap.put("test3", Lists.newArrayList(3, 33, 333));

        List<String> standard = Lists.newArrayList("test2", "test1", "test3");

        Map<String, List<Integer>> result = Maps.newLinkedHashMap();

        standard.stream().forEach(value -> {
            if (hashMap.containsKey(value)) {
                result.put(value, hashMap.get(value));
            }
        });

        System.err.println(result);
    }
}

package io.github.gtang94.finejar.jdk;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tanggq
 * @class MapSort
 * @description JDK1.8 map排序
 * @date 2021/4/25
 **/
public class MapSort {

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
}

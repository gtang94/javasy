package io.github.gtang94.finejar.jdk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author tanggq
 * @class ListStream
 * @description List流操作
 * @date 2021/4/29
 **/
public class ListStream {

    /**
     * @description 通过Stream流过滤List
     * @return: null
     * @author tanggq
     * @date 2021/4/29
     **/
    @Test
    public void ListFilter() {

       final String str = "[" +
                "{\"id\" : 0, \"name\" : \"zhangsan\"}," +
                "{\"id\" : 1, \"name\" : \"lisi\"}," +
                "{\"id\" : 2, \"name\" : \"wangmazi\"}," +
                "{\"id\" : 3, \"name\" : \"tanggq\"}" +
                "]";

        List<Entity> list = JSONArray.parseArray(str, Entity.class);
        List<Entity> collect = list.stream().filter(x -> "3".equals(x.getId())).collect(Collectors.toList());
        System.err.println(collect);
    }

    @Data
    @AllArgsConstructor
    public class Entity {
        private String id;
        private String name;
    }
}

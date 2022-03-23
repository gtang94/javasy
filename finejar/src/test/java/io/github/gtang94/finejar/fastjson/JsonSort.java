package io.github.gtang94.finejar.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

/**
 * @author tanggq
 * @class
 * @description
 * @date 2021/12/30
 */
public class JsonSort {

    String str = "{\n" +
"    \"P000000003\": [\n" +
"        {\n" +
"            \"bunRui\": \"SP\",\n" +
"            \"code\": \"1008\",\n" +
"            \"ins_date\": \"2021-08-24T19:46:02.000+0800\",\n" +
"            \"inventory_cnt\": 3280,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"【ＡＲ】家事郎の一日\",\n" +
"            \"ntm_sale\": 176,\n" +
"            \"product_id\": \"P000000003\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"stock_date\": \"20211103\"\n" +
"        },\n" +
"        {\n" +
"            \"bunRui\": \"SP\",\n" +
"            \"code\": \"1008\",\n" +
"            \"ins_date\": \"2021-08-24T19:46:02.000+0800\",\n" +
"            \"inventory_cnt\": 3280,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"【ＡＲ】家事郎の一日\",\n" +
"            \"ntm_sale\": 176,\n" +
"            \"product_id\": \"P000000003\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"stock_date\": \"20211013\"\n" +
"        }\n" +
"    ],\n" +
"    \"P000000006\": [\n" +
"        {\n" +
"            \"bunRui\": \"AR\",\n" +
"            \"code\": \"1031\",\n" +
"            \"ins_date\": \"2021-08-25T14:49:51.337+0800\",\n" +
"            \"inventory_cnt\": 0,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"カタログギフト ギフトタイム・ボルドー\",\n" +
"            \"ntm_sale\": 0,\n" +
"            \"product_cd\": 3,\n" +
"            \"product_id\": \"P000000006\",\n" +
"            \"product_type\": \"AR\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"sort_no\": 6,\n" +
"            \"stock_date\": \"20210804\"\n" +
"        },\n" +
"        {\n" +
"            \"bunRui\": \"AR\",\n" +
"            \"code\": \"1031\",\n" +
"            \"ins_date\": \"2021-08-25T14:49:51.337+0800\",\n" +
"            \"inventory_cnt\": 0,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"カタログギフト ギフトタイム・ボルドー\",\n" +
"            \"ntm_sale\": 0,\n" +
"            \"product_cd\": 3,\n" +
"            \"product_id\": \"P000000006\",\n" +
"            \"product_type\": \"AR\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"sort_no\": 6,\n" +
"            \"stock_date\": \"20210623\"\n" +
"        },\n" +
"        {\n" +
"            \"bunRui\": \"AR\",\n" +
"            \"code\": \"1031\",\n" +
"            \"ins_date\": \"2021-08-25T14:49:51.337+0800\",\n" +
"            \"inventory_cnt\": 0,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"カタログギフト ギフトタイム・ボルドー\",\n" +
"            \"ntm_sale\": 0,\n" +
"            \"product_cd\": 3,\n" +
"            \"product_id\": \"P000000006\",\n" +
"            \"product_type\": \"AR\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"sort_no\": 6,\n" +
"            \"stock_date\": \"20210811\"\n" +
"        }\n" +
"    ],\n" +
"    \"P000000013\": [\n" +
"        {\n" +
"            \"bunRui\": \"SP\",\n" +
"            \"code\": \"1041\",\n" +
"            \"ins_date\": \"2021-08-24T19:26:01.000+0800\",\n" +
"            \"inventory_cnt\": 265,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"アウトドアＣＰ用・マグカップ＜1箱20個入り＞\",\n" +
"            \"ntm_sale\": 14200,\n" +
"            \"product_id\": \"P000000013\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"stock_date\": \"20211103\"\n" +
"        },\n" +
"        {\n" +
"            \"bunRui\": \"SP\",\n" +
"            \"code\": \"1041\",\n" +
"            \"ins_date\": \"2021-08-24T19:26:01.000+0800\",\n" +
"            \"inventory_cnt\": 265,\n" +
"            \"memo\": \"\",\n" +
"            \"name\": \"アウトドアＣＰ用・マグカップ＜1箱20個入り＞\",\n" +
"            \"ntm_sale\": 14200,\n" +
"            \"product_id\": \"P000000013\",\n" +
"            \"reprint_num\": \"0\",\n" +
"            \"reserve_num\": 0,\n" +
"            \"stock_date\": \"20211013\"\n" +
"        }\n" +
"    ]\n" +
"}";

    @Test
    public void jsonObjectSort() {
        JSONObject jsonObject = JSONObject.parseObject(str);
        HashMap<String, Integer> sortNoMap = Maps.newHashMap();
        JSONObject res = new JSONObject(true);

        Set<String> productIds = jsonObject.keySet();
        for (String key : productIds) {
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            JSONObject first = jsonArray.getJSONObject(0);
            Integer sortNo = Optional.ofNullable(first.getIntValue("sort_no")).orElse(0);
            sortNoMap.put(key, sortNo);
        }

        ArrayList<Map.Entry<String, Integer>> sortNoList = new ArrayList<>(sortNoMap.entrySet());

        Collections.sort(sortNoList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (int i = 0; i < sortNoList.size(); i++) {
            Map.Entry<String, Integer> stringIntegerEntry = sortNoList.get(i);
            res.put(stringIntegerEntry.getKey(), jsonObject.getJSONArray(stringIntegerEntry.getKey()));
        }

        System.err.println(sortNoList);
        System.err.println(res);

    }


}

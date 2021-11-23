package io.github.gtang94.finejar.mini;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @class: test
 * @description:
 * @author: tanggq
 * @date: 5/7/21
 * @version: 1.0
 */
public class test {

    @Test
    public void test1() {
        System.err.println(Math.round(-1.5));
    }

    @Test
    public void test2() {
        System.err.println(new StringBuffer("ABCD").reverse().toString());
    }

    @Test
    public void test3() {
        String strArr = "[{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}," +
                "{\"00\":\"zhangsan\",\"11\":\"lisi\",\"22\":\"wangwu\",\"33\":\"maliu\"}]";

        List<Map<String,String>> listObjectFir = (List<Map<String,String>>) JSONArray.parse(strArr);
        System.out.println("利用JSONArray中的parse方法来解析json数组字符串");
        for(Map<String,String> mapList : listObjectFir){
            for (Map.Entry entry : mapList.entrySet()){
                System.out.println( entry.getKey()  + "  " +entry.getValue());
            }
        }

    }
}

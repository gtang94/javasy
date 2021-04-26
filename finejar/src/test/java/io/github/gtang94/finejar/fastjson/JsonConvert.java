package io.github.gtang94.finejar.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import io.github.gtang94.finejar.jdk.reflect.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanggq
 * @class JsonConvert
 * @description Json格式的转换
 * @date 2021/4/26
 **/
public class JsonConvert {

    private static final String SIMPLE_STRING = "{"
            + "\"name\" : \"zhangsan\","
            + "\"age\" : \"25\""
            + "}";

    private static final String STRING_ARRAY = "[{"
            + "\"name\" : \"zhangsan\","
            + "\"age\" : \"25\""
            + "},"
            + "{"
            + "\"name\" : \"lisi\","
            + "\"age\" : \"30\""
            + "}]";
    private static final String COMPLEX_STRING = "{"
            + "\"name\"     : \"zp\","
            + "\"age\"  : 12,"
            + "\"has_g\"    : true,"
            + "\"major\"    : [\"first\", \"second\"],"
            + "\"other\"    : {"
            +    "\"birthday\"  : \"1990-01-01\","
            +    "\"like\"      : \"study\""
            +   "}"
            + "}";

    /**
     * @description 简单json格式的string和JSONObject的转换, String->JSONObject & JSONObject -> String
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void simpleString2JSONObject() {
        JSONObject jsonObject = JSONObject.parseObject(SIMPLE_STRING);

        // 方式1
        System.err.println( JSONObject.toJSONString(jsonObject) );
        // 方式2
        System.err.println( jsonObject.toJSONString() );
    }

    /**
     * @description 数组格式字符串 转 JSONArray及遍历 String -> JSONArray
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void stringArray2JSONArray() {
        JSONArray jsonArray = JSONArray.parseArray(STRING_ARRAY);

        // 遍历方式1
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            System.err.println(jsonObject.getString("name") + " " + jsonObject.getString("age"));
        }
        // 遍历方式2
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.err.println(jsonObject.getString("name") + " " + jsonObject.getString("age"));
        }
    }

    /**
     * @description 复杂格式字符串 转 JSONObject complex string -> JSONObject
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void complexString2JSONObject() {
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_STRING);

        // 获取转换后的JSONObject中的字符串
        String name = jsonObject.getString("name");
        String age = jsonObject.getString("age");
        String hasG = jsonObject.getString("has_g");

        // 获取转换后的JSONObject中的数组
        JSONArray major = jsonObject.getJSONArray("major");
        for (Object object : major) {
            String entity = (String) object;
        }

        // 获取转换后的JSONObject中的对象
        JSONObject otherJSONObject = jsonObject.getJSONObject("other");
        String birthday = otherJSONObject.getString("birthday");
        String like = otherJSONObject.getString("like");
    }

    /**
     * @description 简单格式字符串 转 Java对象， string -> JavaBean
     * @return: null
     * @throws
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void simpleString2JavaBean() {
        JSONObject jsonObject = JSONObject.parseObject(SIMPLE_STRING);

        // 方式1
        String name = jsonObject.getString("name");
        String age = jsonObject.getString("age");
        Person person1 = new Person(name, age);
        System.err.println(person1.toString());

        // 方式2
        Person person2 = JSONObject.parseObject(SIMPLE_STRING, new TypeReference<Person>(){});
        System.err.println(person2);

        // 方式3
        Person person3 = JSONObject.parseObject(SIMPLE_STRING, Person.class);
        System.err.println(person3);
    }

    /**
     * @description Java对象 转 字符串， JavaBean -> String
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void javaBean2SimpleString() {
        Person person = new Person("tanggq", "26");
        String jsonString = JSONObject.toJSONString(person);
        System.err.println(jsonString);
    }

    /**
     * @description 数组格式字符串 转 List<Object>  string -> List
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void stringArray2JavaBeanList() {
        // 方式1
        JSONArray jsonArray = JSONArray.parseArray(STRING_ARRAY);
        List<Person> personList1 = Lists.newArrayList();
        for (Object object : jsonArray) {
            Person bean  = new Person(((JSONObject)object).getString("name"), ((JSONObject)object).getString("age"));
            personList1.add(bean);
        }
        System.err.println(personList1.toString());

        // 方式2
        List<Person> personList2 = JSONArray.parseObject(STRING_ARRAY, new TypeReference<ArrayList<Person>>(){});
        System.err.println(personList2.toString());

        // 方式3
        List<Person> personList3 = JSONArray.parseArray(STRING_ARRAY, Person.class);
        System.err.println(personList3.toString());
    }

    /**
     * @description List<Object> 转 数组格式字符串 List -> string
     * @return: null
     * @throws
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void javaBeanList2StringArray() {
        Person person1 = new Person("tanggq", "26");
        Person person2 = new Person("gtang94", "26");
        List<Person> personList = Lists.newArrayList(person1, person2);
        System.err.println( JSONArray.toJSONString(personList) );
    }

    /**
     * @description JavaBean 转 JSONObject
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void javaBean2JSONObject() {
        Person person1 = new Person("tanggq", "26");
        // 方式1
        String jsonString =JSONObject.toJSONString(person1);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonString);
        System.err.println(jsonObject1);

        // 方式2
        JSONObject jsonObject2 = (JSONObject) JSONObject.toJSON(person1);
        System.err.println(jsonObject2);
    }

    /**
     * @description JSONObject 转 JavaBean
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void JSONObject2JavaBean() {
        JSONObject jsonObject = JSONObject.parseObject(SIMPLE_STRING);

        Person person1 = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Person>(){});
        System.err.println(person1);

        Person person2 = JSONObject.parseObject(jsonObject.toJSONString(), Person.class);
        System.err.println(person2);
    }

    /**
     * @description JavaList 转 JsonArray
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void javaList2JsonArray() {
        Person person1 = new Person("tanggq", "26");
        Person person2 = new Person("gtang94", "26");
        List<Person> personList = Lists.newArrayList(person1, person2);

        // 方式1
        String jsonString = JSONArray.toJSONString(personList);
        JSONArray jsonArray1 = JSONArray.parseArray(jsonString);
        System.err.println(jsonArray1);

        // 方式2
        JSONArray jsonArray2 = (JSONArray) JSONArray.toJSON(personList);
        System.err.println(jsonArray2);
    }

    /**
     * @description JSONArray -> JavaList
     * @return: null
     * @author tanggq
     * @date 2021/4/26
     **/
    @Test
    public void jsonArray2JavaList() {
        JSONArray jsonArray = JSONArray.parseArray(STRING_ARRAY);

        ArrayList<Person> personList1 = JSONArray.parseObject(jsonArray.toJSONString(), new TypeReference<ArrayList<Person>>(){});
        System.err.println(personList1);

        List<Person> personList2 = JSONArray.parseArray(jsonArray.toJSONString(), Person.class);
        System.err.println(personList2);
    }


    @Data
    @AllArgsConstructor
    @ToString
    public class Person {
        private String name;
        private String age;
    }
}

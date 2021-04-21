package io.github.gtang94.finejar.jdk.reflect;

/**
 * @author tanggq
 * @class Person
 * @description 反射使用类，人
 * @date 2021/4/21
 **/
public class Person extends Animal {

    public String commonName = "人";

    protected Integer commonFingerCnt = 20;

    private Integer commonEye = 2;

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private void say() {
        System.out.println("Person 在 say()");
    }

    public Integer getCommonEye() {
        return commonEye;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

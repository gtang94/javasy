package io.github.gtang94.spring.ioc;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlLoad {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Object person = context.getBean("person");
        System.err.println(person.toString());

    }
}

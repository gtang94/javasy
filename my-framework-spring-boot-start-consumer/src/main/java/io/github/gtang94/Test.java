package io.github.gtang94;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class Test {

    @Autowired
    MyFrameworkSpringBootStartProvider provider;


    @org.junit.jupiter.api.Test
    public void test1() {
        System.out.println(
            provider.loadEligibleBean()
        );
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        System.out.println(
            provider.loadNoEligibleBean()
        );
    }
}

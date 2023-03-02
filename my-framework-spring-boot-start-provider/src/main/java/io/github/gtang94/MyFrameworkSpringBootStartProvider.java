package io.github.gtang94;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFrameworkSpringBootStartProvider {

    @Bean
    @ConditionalOnClass(Integer.class)
    public String loadEligibleBean() {
        return "加载符合条件的Bean";
    }

    @Bean
    @ConditionalOnMissingBean(Integer.class)
    public String loadNoEligibleBean() {
        return "加载不符合条件的Bean";
    }
}

package spring01.core.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring01.core.discount.DiscountPolicy;
import spring01.core.discount.FixDiscountPolicy;
import spring01.core.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시 상속받은 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회 시 상속받은 자식이 둘 이상 있으면 빈 이름을 지정해주면 된다")
    void findBeanByParentTypeBeanName() {

        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);

        for(String key: beansOfType.keySet()) {
            System.out.println("key : " + key + " value: " + beansOfType.get(key));
        }
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for(String key: beansOfType.keySet()) {
            System.out.println("key : " + key + " value: " + beansOfType.get(key));
        }
    }



    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

}

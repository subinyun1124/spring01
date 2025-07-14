package spring01.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring01.core.discount.DiscountPolicy;
import spring01.core.discount.FixDiscountPolicy;
import spring01.core.discount.RateDiscountPolicy;
import spring01.core.member.MemberService;
import spring01.core.member.MemberServiceImpl;
import spring01.core.member.MemberRepository;
import spring01.core.member.MemoryMemberRepository;
import spring01.core.order.OrderService;
import spring01.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

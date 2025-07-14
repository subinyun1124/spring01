package spring01.core;

import spring01.core.discount.DiscountPolicy;
import spring01.core.discount.FixDiscountPolicy;
import spring01.core.member.MemberService;
import spring01.core.member.MemberServiceImpl;
import spring01.core.member.MemberRepository;
import spring01.core.member.MemoryMemberRepository;
import spring01.core.order.OrderService;
import spring01.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}

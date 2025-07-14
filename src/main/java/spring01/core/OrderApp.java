package spring01.core;

import spring01.core.member.Grade;
import spring01.core.member.Member;
import spring01.core.member.MemberService;
import spring01.core.member.MemberServiceImpl;
import spring01.core.order.Order;
import spring01.core.order.OrderService;
import spring01.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
    }
}

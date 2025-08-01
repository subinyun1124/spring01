package spring01.core.singleton;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring01.core.AppConfig;
import spring01.core.member.MemberService;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출 시 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출 시 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //3. 참조값이 다른 것 확인
        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 : " + singletonService1);
        System.out.println("singletonService2 : " + singletonService2);

        // isEqual: 값 비교
        // isSame: 참조값(주소) 비교
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void SpringContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }

}

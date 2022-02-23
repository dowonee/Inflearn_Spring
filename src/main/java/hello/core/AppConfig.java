package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정정보
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderSerivice -> new MemoryMemberRepository()
    // 각각 다른 2개의 new MemoryMemberRepository() 가 생성되면서 싱글톤이 깨지는것 처럼 보인다.
    // 스프링컨테이너는 이 문제를 어떻게 해결할까?
    // 싱글톤이 깨질까 안깨질까? -> 테스트해본다.


    //@Bean 을 쓰면 스프링 컨테이너에 등록됨
    @Bean
    // 멤버서비스 구현체인 객체가 생성됨(MemberServiceImpl)
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {

        return new RateDiscountPolicy();
    }


}

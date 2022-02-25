package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 기능을 실행하는 책임만 져야한다.
@Component
//@RequiredArgsConstructor //final이 붙은것을 가지고 생성자를 만들어줌
public class OrderServiceImpl implements OrderService{

    //DIP를 지키는 방식 => 인터페이스에만 의존
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 가끔 생성자가 직접 필요할 때만 직접 만든다.

    // 최근에는 생성자를 딱 하나두고, @Autowired를 생략하는 방법을 주로 사용한다.
    // 여기에 Lombok 라이브버리의 @RequiredArgsConstructor 를 함께 사용하면 기능은 다 제공하면서 코드는 깔끔하게 사용할 수 있다.

    //@Qualifier("mainDiscountPolicy") 로 주입할때 @Qualifier("mainDiscountPolicy")를 못찾으면
    //mainDiscountPolicy 라는 이름의 스프링 빈을 추가로 찾는다. 하지만 @Qualifier는 @Qualifier를 찾는 용도로만 사용하는게
    //명확하고 좋다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문생성 요청이 오면 회원정보 조회하고
        Member member = memberRepository.findById(memberId);

        // 할인정책에 정보넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

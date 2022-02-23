package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 기능을 실행하는 책임만 져야한다.
@Component("service")
public class OrderServiceImpl implements OrderService{

    //DIP를 지키는 방식 => 인터페이스에만 의존
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 생성자있는건 웬만한건 다 값을 다 채워넣어야한다.
    // 주로 불변이면서 필수인 의존관계
    // 생성자가 딱 하나만 있으면 @Autowired 생략가능

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

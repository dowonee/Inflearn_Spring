package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// 자동으로 스프링빈으로 등록시킨다.
// excludeFilters : 그중에서 뺄걸 지정해주는 것
// 컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에
// AppCOnfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록, 실해되어버린다.
// 그래서 excludeFilters 를 이용해서 설정 정보는 컴포넌트 스캔대상에서 제외한다.
// 보통 설정 정보를 컴포넌트 스캔 대상에서 제외하진 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이방법을 선택했다.

// basePackages : 탐색할 패키지의 시작위치를 지정한다. 입력한 경로를 포함해 하위로 찾아나감
// hello.core.member => 이렇게하면 멤버만 component의 스캔 대상이 됨
// basePackages = {"hello.core", "hello.service"} 이렇게 시작위치 지정가능
// basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
// 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다. 이 클래스에서는 hello.core 가 디폴트가 된다.

public class AutoAppConfig {


    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

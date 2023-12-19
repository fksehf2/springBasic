package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    * 생성자 주입,  호출시점에 1번만 호출됨 (불변,필수) 의존관계에 사용, 생성자가 1개만 있으면 autowired 없어도 인식함,
    * 수정자 주입,  setter라 불리는 필드의 값을 변경하려는 수정자 메서드를 통해서 의존 관계를 주입 (선택, 변경) 의존 관계에 사용
    * 필드 주입, @autowired private MemoryRepository memoryRepository 코드는 간결하지만 외부에서 변경이 불가능한 단점. 사용ㄴㄴ
    * 일반 메서드 주입, 한번에 여러 필드 주입 가능,
    */

//    lombok @RequiredArgsConstructor 가 final 붙은 field를 모아 생성자를 자동으로 생성해줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
    * autowired 매핑 규칙
    * 1. 타입
    * 2. 타입 매칭 결과가 2개 이상 일 때 필드 명이나 파라미터명으로 빈 이름 매칭
    * 3. @Quillifier 사용
    * 4. @Primary 사용 (우선 사용하겠다는 의미)
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

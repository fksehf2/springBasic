package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
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
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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

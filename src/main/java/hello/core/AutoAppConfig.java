package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", //member패키지만 컴포넌트 대상
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository (){
//        return new MemoryMemberRepository();
//    }

}

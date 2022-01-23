package hello.hellospring.configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    2, 3 방식
//    private final DataSource dataSource;
//    public SpringConfig(DataSource dataSource) { this.dataSource = dataSource; }

//    4 방식
//    private EntityManager em;
//    public SpringConfig(EntityManager em) { this.em = em; }

//    1, 2, 3, 4 방식
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }

//    5. 방식
    private final SpringDataJpaMemberRepository memberRepository;
    public SpringConfig(SpringDataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        /* 1. 메모리 방식 injection */
//        // return new MemoryMemberRepository();
//        /* 2. JDBC 방식 injection */
//        // return new JdbcMemberRepository(dataSource);
//        /* 3. JDBC template 방식 injection */
//        // return new JdbcTemplateMemberRepository(dataSource);
//        /* 4. JPA 방식 injection */
//        // return new JpaMemberRepository(em);
//    }

    /* AOP 등록
     * TimeTraceAop.java에 @Components 보다 이렇게 등록하여 AOP 임을 인지시키는 게 좋음
     * 다만 @Around 대상에 빈 등록 파일을 제외시켜주어야 함
     * !target(hello.hellospring.configuration.SpringConfig)
     **/
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

}

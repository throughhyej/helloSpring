package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("spring");

        Long memberId = service.join(member);
        Member findMember = service.findOne(memberId).get();

        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원가입() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        service.join(member1);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> service.join(member2));
        org.assertj.core.api.Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}
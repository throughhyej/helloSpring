package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach() {
        // 각 테스트 케이스는 독립적으로 실행되어야 하기 때문에 beforeEach
        // 같은 MemoryMemberRepository를 사용하기 위해 DI: repository
        // 당장은 MemoryMemberRepository.java에 static 선언되어 있어 문제가 발생하진 않음
        // static이 아닐 경우, 다른 인스턴스이기 때문에 데이터가 어긋날 수 있음
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

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

//        try {
//            service.join(member2);
//            fail();
//        }catch (IllegalStateException exception) {
//            org.assertj.core.api.Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
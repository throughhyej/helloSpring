package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("member1");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        org.junit.jupiter.api.Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result) ;
    }

    @Test
    void findById() {
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member1");
        repository.save(member2);

        Member member = repository.findById(member1.getId()).get();
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(member1);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member1");
        repository.save(member2);

        List<Member> allMember = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(allMember.size()).isEqualTo(2);
    }

    @Test
    void findAll() {
    }
}
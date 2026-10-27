package com.shin.springbootdevelopers;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// 각 @Test 메소드는 끝나면 자동으로 롤백 되기 때문에 데이터가 변경된 내역이 저장되지 않음
@DataJpaTest
class MemberRepositoryTest {
    // 사용자를 대신해서 엔티티 매니저를 관리하여 데이터베이스 접근
    @Autowired
    MemberRepository memberRepository;

    // 테스트 메소드와 저장소가 함께 사용할 엔티티 매니저
    @Autowired
    EntityManager entityManager;

    @AfterEach
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    // 모든 회원 조회
    @Sql("/insert-members.sql")
    @Test
    void getAllMembers(){
        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(3);
    }

    // 특정 Id 회원 조회
    @Sql("/insert-members.sql")
    @Test
    void getMemberById(){
        // when
        Member member = memberRepository.findById(2L).get();

        // then
        assertThat(member.getName()).isEqualTo("B");
    }

    // 특정 Name 회원 조회
    @Sql("/insert-members.sql")
    @Test
    void getMemberByName(){
        // when
        Member member = memberRepository.findByName("C").get();

        // then
        assertThat(member.getId()).isEqualTo(3);
    }

    // 회원 한명을 DB에 추가
    @Test
    void saveMember(){
        // given
        Member member = new Member("A");

        // when
        Member save = memberRepository.save(member);

        // then
        assertThat(save.getName()).isEqualTo("A");
    }

    // 회원 여러명을 DB에 추가
    @Test
    void saveMembers(){
        // given
        List<Member> members = List.of(new Member("B"),
                new Member("C"));

        // when
        memberRepository.saveAll(members);
        memberRepository.flush();

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    // 특정 Id 회원 삭제
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById(){
        // when
        memberRepository.deleteById(2L);

        // then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    // 모든 회원 삭제
    @Sql("/insert-members.sql")
    @Test
    void deleteAll(){
        // when
        memberRepository.deleteAll();

        // then
        assertThat(memberRepository.findAll().size()).isZero();
    }
}
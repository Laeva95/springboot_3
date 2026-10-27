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
        // deleteById(2L) 으로 Id=2 인 행을 삭제했을 때
        // 실제로 바로 DB에서 삭제되는 것이 아니라 삭제 예정 상태가 됨
        // JPA는 삭제 예정인 객체를 없는 객체로 취급

        // when
        memberRepository.deleteById(2L);

        // then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    // 모든 회원 삭제
    @Sql("/insert-members.sql")
    @Test
    void deleteAll(){
        // 테이블의 모든 객체를 삭제 예정 상태로 변경
        // 이후 flush()를 통해 삭제

        // when
        memberRepository.deleteAll();

        // then
        assertThat(memberRepository.findAll().size()).isZero();
    }

    // 특정 Id 회원 수정
    @Sql("/insert-members.sql")
    @Test
    void update(){
        // @DataJpaTest 어노테이션에 의해 @Transactional 어노테이션을 붙이지 않더라도
        // 업데이트를 성공적으로 수행
        // Id=2 행인 엔티티 객체를 가져와서 changeName 메소드를 통해 name을 변경
        // 엔티티 매니저의 flush() 메소드를 통해 데이터베이스에 변경 사항 업데이트
        // 다시 데이터베이스에서 Id=2 행의 객체를 가져왔을 때 변경된 엔티티 객체를 가져옴

        // given
        Member member = memberRepository.findById(2L).get();

        // when
        member.changeName("BC");

        entityManager.flush();
        entityManager.clear();

        // 값을 "CB"로 변경 하더라도, 엔티티 매니저의 clear() 메소드를 통해
        // 엔티티와 데이터베이스의 연결이 끊어졌으므로 변경사항이 데이터베이스에 업데이트되지 않음
        member.changeName("CB");

        // then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }
}
package com.shin.springbootdevelopers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JpaRepository를 구현한 인터페이스
@Repository
// JpaRepository<Member, Long>: 저장할 엔티티와 Id 값을 입력
public interface MemberRepository extends JpaRepository<Member,Long> {
    // 쿼리메소드: 개발자가 직접 메소드 이름 규칙으로 SQL을 실행하는 메소드
    // 스프링 데이터 JPA가 이 쿼리 메소드를 읽고 자동으로 SQL 구문을 생성함
    Optional<Member> findByName(String name);
}

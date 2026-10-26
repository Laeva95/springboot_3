package com.shin.springbootdevelopers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository를 구현한 인터페이스
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    // JpaRepository<Member, Long>: 저장할 엔티티와 Id 값을 입력
}

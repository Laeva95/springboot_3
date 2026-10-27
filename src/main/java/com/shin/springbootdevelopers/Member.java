package com.shin.springbootdevelopers;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 모든 변수에 getter 메소드를 추가하는 어노테이션
@Getter

// DB의 테이블 행과 연결하는 JPA 어노테이션
@Entity

// 매개변수가 없는 생성자 자동 작성
// JPA는 db에서 행을 조회할 때 매개변수가 없는 생성자로 객체를 생성 후
// 변수마다 값을 채워 넣는 방식
// PROTECTED: 개발자가 새 객체를 직접 만드는 것을 막음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false) // updatable: 값 수정 가능 여부
    private Long id;        // 회원 id

    @Column(name = "name", nullable = false)// nullable: null 값 가능 여부
    private String name;    // 회원 이름

    // 이름을 매개변수로 전달 받는 생성자
    public Member(String name){
        this.name = name;
    }

    // 매개변수를 통해 이름을 변경하는 메소드
    public void changeName(String name){
        this.name = name;
    }
}

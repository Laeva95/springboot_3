package com.shin.springbootdevelopers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    // Service 객체 클래스임을 표시
public class TestService {
    @Autowired
    private MemberRepository memberRepository;

    // db에 저장된 회원 목록을 조회해서 반환
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}

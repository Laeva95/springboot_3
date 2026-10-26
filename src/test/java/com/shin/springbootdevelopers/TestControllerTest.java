package com.shin.springbootdevelopers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
                // 스프링 컨테이너가 만들어지고 객체 자동 생성
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성
                        // MockMvc: 브라우저 요청을 흉내내서 테스트하는 도구
class TestControllerTest {

    // 톰캣 서버 실행시 객체 자동 주입
    @Autowired
    protected MockMvc mockMvc;

    // 톰캣 서버 실행시 객체 자동 주입
    @Autowired
    private WebApplicationContext context;

    // 톰캣 서버 실행시 객체 자동 주입
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach // 테스트 실행 전 마다 실행하는 메서드
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        memberRepository.deleteAll();
    }

    @AfterEach // 테스트 실행 후 마다 실행하는 메서드
    public void cleanUp() {
        // member 테이블의 행 데이터를 모두 삭제
        memberRepository.deleteAll();
    }

    @DisplayName("TestController.java에 작성된 getAllMembers 메소드 테스트:" +
            " 회원 목록 조회에 성공하는지 테스트")
    @Test
    public void getAllMembers() throws Exception {
        // given
        // 요청을 보낼 주소
        final String url = "/test";
        // db에 저장할 테스트 데이터
        Member savedMember = memberRepository.save(new Member("홍길동"));

        // when
        // get /test 요청 보냄
        // json으로 응답을 받음
        final ResultActions result = mockMvc.perform(get(url).
                accept(MediaType.APPLICATION_JSON));

        // then
        // status().isOk(): 응답 코드가 200인지 확인
        // db의 0 인덱스 데이터가 테스트 데이터와 동일한지 확인
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}
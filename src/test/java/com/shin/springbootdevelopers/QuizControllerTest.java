package com.shin.springbootdevelopers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest         // 스프링부트 테스트. 프로젝트 설정 파일을 읽고 스프링 빈을 생성해서 컨테이너에 보관
@AutoConfigureMockMvc   // MockMvc 객체를 만들어 스프링 컨테이너에 보관
public class QuizControllerTest {
    @Autowired
    // 서버를 실행하지 않고 HTTP 요청과 응답을 테스트 할 수 이는 도구
    protected MockMvc mockMvc;

    @Autowired
    // 컨트롤러를 포함한 스프링이 보관하는 스프링 빈 정보가 들어 있는 변수
    private WebApplicationContext context;

    @Autowired
    // post 요청 테스트에서 자바 객체 Code를 json 문자열로 바꾸는 데 사용
    private ObjectMapper objectMapper;

    @BeforeEach
    // 각 @Test 실행 전에 mockMvc 변수에 객체를 생성해서 초기화하는 메소드
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception {
        // given
        // GET 요청 주소를 저장
        final String url = "/quiz";

        // when
        // get(): GET 방식으로 url 정보를 담은 객체 생성
        // param(): name="code", value="1"으로 파라미터 생성
        // mockMvc.perform(): 요청 객체 정보로 ResultActions 객체를 생성
        final ResultActions result = mockMvc.perform(get(url)
                                        .param("code", "1"));

        // then
        // andExpect(): 매개변수로 받은 검사 조건에 응답하고, 조건에 틀리면 테스트 실패
        // status().isCreated(): 응답 상태 코드가 201인지 검사
        // content().string("Created!"): 응답 본문이 "Created!" 인지 검사
        result.andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception {
        // given
        // GET 요청 주소를 저장
        final String url = "/quiz";

        // when
        // get(): GET 방식으로 url 정보를 담은 객체 생성
        // param(): name="code", value="2"으로 파라미터 생성
        // mockMvc.perform(): 요청 객체 정보로 ResultActions 객체를 생성
        final ResultActions result = mockMvc.perform(get(url)
                                        .param("code", "2"));

        // then
        // andExpect(): 매개변수로 받은 검사 조건에 응답하고, 조건에 틀리면 테스트 실패
        // status().isBadRequest(): 응답 상태 코드가 400인지 검사
        // content().string("Bad Request!"): 응답 본문이 "Bad Request!" 인지 검사
        result.andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    @DisplayName("quiz(): POST /quiz?=code1 이면 응답 코드는 403, 응답 본문은 Forbidden!을 리턴한다.")
    @Test
    public void postQuiz1() throws Exception {
        // given
        // POST 요청 url 저장
        final String url = "/quiz";

        // when
        // post(): POST 방식으로 url 정보를 담은 객체 생성
        // contentType(): 요청 본문에 담긴 데이터가 json 타입임을 서버에 알림
        // MediaType.APPLICATION_JSON: json 타입 상수
        // content(): json 타입인 매개 변수를 요청 본문으로 전달
        // objectMapper.writeValueAsString(): java 객체를 json 데이터로 변환
        final ResultActions result = mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(new Code(1))));

        // then
        // andExpect(): 매개변수로 받은 검사 조건에 응답하고, 조건에 틀리면 테스트 실패
        // status().isForbidden(): 응답 상태 코드가 403인지 검사
        // content().string("Forbidden!"): 응답 본문이 "Forbidden!" 인지 검사
        result.andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz(): POST /quiz?=code13 이면 응답 코드는 200, 응답 본문은 OK!를 리턴한다.")
    @Test
    public void postQuiz13() throws Exception {
        // given
        // POST 요청 url 저장
        final String url = "/quiz";

        // when
        // post(): POST 방식으로 url 정보를 담은 객체 생성
        // contentType(): 요청 본문에 담긴 데이터가 json 타입임을 서버에 알림
        // MediaType.APPLICATION_JSON: json 타입 상수
        // content(): json 타입인 매개 변수를 요청 본문으로 전달
        // objectMapper.writeValueAsString(): java 객체를 json 데이터로 변환
        final ResultActions result = mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(new Code(13))));

        // then
        // andExpect(): 매개변수로 받은 검사 조건에 응답하고, 조건에 틀리면 테스트 실패
        // status().isOK(): 응답 상태 코드가 200인지 검사
        // content().string("OK!"): 응답 본문이 "OK!" 인지 검사
        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}

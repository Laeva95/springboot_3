package com.shin.springbootdevelopers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 퀴즈 실습용 컨트롤러.
// 응답 상태 코드 200, 201, 400, 403을 직접 골라서 연습
@RestController
public class QuizController {
    @GetMapping("/quiz")
    // ResponseEntity<>: 요청 상태 값과 데이터를 담아서 반환
    // @RequestParam: 전달받은 parameter 값을 code라는 이름으로 사용
    public ResponseEntity<String> quiz(@RequestParam("code") int code) {
        switch (code) {
            case 1: // code 값이 1일 경우
                return ResponseEntity.created(null).body("Created!");
            case 2: // code 값이 2일 경우
                return ResponseEntity.badRequest().body("Bad Request!");
            default:// code 값이 그 외일 경우
                return ResponseEntity.ok().body("OK!");
        }
    }

    @PostMapping("/quiz")
    // @RequestBody: 브라우저가 요청 보낸 JSON 본문을 Code 객체로 변경해서 code 변수로 사용
    public ResponseEntity<String> quiz2(@RequestBody Code code){
        switch (code.value()){
            case 1: // code 값이 1일 경우
                return ResponseEntity.status(403).body("Forbidden!");
            default:// code 값이 그 외일 경우
                return ResponseEntity.ok().body("OK!");
        }
    }
}
// record Code: 값 하나를 담는 간단한 데이터 클래스
// record가 자동으로 만들어 주는 것: new Code(), value()
record Code(int value){}
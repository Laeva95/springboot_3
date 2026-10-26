import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 테스트란 무엇인가 연습
public class JUnitTest {
    // @DisplayName(""): 테스트 결과 화면에 보여줄 이름
    @DisplayName("1 + 2은 3이다")
    // @Test: 아래의 메소드를 테스트로 실행
    @Test
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        // 3과 a + b가 동일한지 검증
        // 기대값, 검증값을 입력
        Assertions.assertEquals(sum, a + b);
    }
    @DisplayName("1 + 3은 3이다")
    @Test
    public void junitFailedTest(){
        int a = 1;
        int b = 3;
        int sum = 3;

        // 3과 a + b가 동일한지 검증
        // 기대값, 검증값을 입력
        Assertions.assertEquals(sum, a + b);
    }
}

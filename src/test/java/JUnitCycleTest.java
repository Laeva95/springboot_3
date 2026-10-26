import org.junit.jupiter.api.*;
public class JUnitCycleTest {
    // 전체 테스트를 시작하기 전에 1회 실행. static 필수
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }
    // 테스트 케이스를 실행하기 전마다 1회 실행
    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("test1 메소드 테스트 진행");
    }
    @Test
    public void test2(){
        System.out.println("test2 메소드 테스트 진행");
    }
    @Test
    public void test3(){
        System.out.println("test3 메소드 테스트 진행");
    }

    // 전체 테스트를 종료하기 전에 1회 실행. static 필수
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }
    // 테스트 케이스를 종료하기 전마다 1회 실행
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}

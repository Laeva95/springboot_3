import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {
    @Test
    public void junitQuiz3(){
        System.out.println("This is a first Test");
    }

    @Test
    public void junitQuiz4(){
        System.out.println("This is a second Test");
    }

    @BeforeEach
    public void junitBeforeEach(){
        System.out.println("Hello");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Bye");
    }
}

package josbot.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodoToString() {
        assertEquals("[T][ ] plan holiday", new Todo("plan holiday").toString());
    }

}
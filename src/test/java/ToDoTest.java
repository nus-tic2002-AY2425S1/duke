import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void getTaskType() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("T", todo.getTaskType(), "Task type for ToDo should be 'T'");
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("Read a book");

        // Expected output format for a ToDo task
        String expected = "[T][ ] Read a book";
        assertEquals(expected, todo.toString(), "toString should return formatted ToDo string");

        // After marking as done
        todo.markAsDone();
        expected = "[T][✔] Read a book";
        assertEquals(expected, todo.toString(), "toString should show ToDo as done after marking");
    }
}
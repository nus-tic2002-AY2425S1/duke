import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void markAsDone() {
        Task task = new Task("Read a book");
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done");
    }

    @Test
    void unmarkAsDone() {
        Task task = new Task("Read a book");
        // Mark task as done first
        task.markAsDone();
        // unmark it
        task.unmarkAsDone();
        assertFalse(task.isDone, "Task should be marked as not done");
    }

    @Test
    void getStatusIcon(){
        Task task = new Task("Read a book");

        // Test for the task before set it to complete
        assertEquals(" ",task.getStatusIcon(), "Status Icon should be blank if task have not complete");

        // Test after the task is complete
        task.markAsDone();
        assertEquals("✔", task.getStatusIcon(), "Status Icon should be tick if task has complete");
    }

    @Test
    void testToString(){
        Task task = new Task("Read a book");

        // Expected output for normal task type
        String expected = "[ ][ ] Read a book";
        assertEquals(expected, task.toString(), "To String should return formatted task string");

        // After the task is done
        task.markAsDone();
        expected = "[ ][✔] Read a book";
        assertEquals(expected, task.toString(), "To String should show task as done.");
    }

    @Test
    void testToFileString() {
        Task task = new Task("Read a book");

        // Expected format for duke.txt file for undone task
        String expected = "  | 0 | Read a book";
        assertEquals(expected, task.toFileString(), "toFileString should return formatted string for file storage");

        // Expected format for duke.txt file for done task
        task.markAsDone();
        expected = "  | 1 | Read a book";
        assertEquals(expected, task.toFileString(), "toFileString should indicate task completion after marking as done");
    }

}
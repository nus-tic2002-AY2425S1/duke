package duke.dancepop;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setup() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
        TaskList.clear();
    }

    @ParameterizedTest()
    @MethodSource("getTasks")
    void Given_Task_When_Add_Should_AddTaskCorrectly(Task task) {
        TaskList.add(task);

        String output = outputStream.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains(task.toString()));
        assertTrue(output.contains("Now you have 1 tasks in the list."));
    }

    @Test()
    void Given_Tasks_When_Print_Should_PrintTaskCorrectly() {
        Task todo = new Todo("description");
        Task deadline = new Deadline("description", "deadline");
        Task event = new Event("description", "start", "end");

        TaskList.add(todo);
        TaskList.add(deadline);
        TaskList.add(event);

        TaskList.print();

        String output = outputStream.toString();
        assertTrue(output.contains("Here are the tasks in your list:"));
        assertTrue(output.contains("1. " + todo));
        assertTrue(output.contains("2. " + deadline));
        assertTrue(output.contains("3. " + event));
    }

    private static Stream<Task> getTasks() {
        return Stream.of(
                new Todo("description"),
                new Deadline("description", "deadline"),
                new Event("description", "start", "end")
        );
    }
}

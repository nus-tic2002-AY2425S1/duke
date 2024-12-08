package duke.dancepop;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;
import duke.dancepop.utils.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {
    private static final LocalDateTime startDT = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
    private static final LocalDateTime endDT = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
    private ByteArrayOutputStream outputStream;

    private static Stream<Task> getTasks() {
        return Stream.of(
                new Todo("description"),
                new Deadline("description", startDT),
                new Event("description", startDT, endDT)
        );
    }

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
    void Given_Task_When_AddAndPrint_Should_AddTaskCorrectly(Task task) {
        TaskList.addAndPrint(task);

        String output = outputStream.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains(task.toString()));
        assertTrue(output.contains("Now you have 1 tasks in the list."));
    }

    @Test()
    void Given_Tasks_When_Print_Should_PrintTaskCorrectly() {
        Task todo = new Todo("description");
        Task deadline = new Deadline("description", startDT);
        Task event = new Event("description", startDT, endDT);

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

    @Test
    void Given_Task_When_Add_Should_AddTaskCorrectly() {
        Task task = new Todo("description");
        TaskList.add(task);
        assertFalse(TaskList.getTasks().isEmpty());
    }

    @Test
    void Given_Task_When_MarkDone_Should_MarkTaskAsDone() {
        Task task = new Todo("description");
        TaskList.add(task);
        TaskList.markDone(0);

        String output = outputStream.toString();
        assertTrue(output.contains("Nice! I've marked this task as done:"));
        assertTrue(output.contains(task.toString()));
    }

    @Test
    void Given_Task_When_UnmarkDone_Should_MarkTaskAsNotDone() {
        Task task = new Todo("description");
//        task.setIsDone(true);
        TaskList.add(task);

        TaskList.unmarkDone(0);
        String output = outputStream.toString();
        assertTrue(output.contains("OK, I've marked this task as not done yet:"));
        assertTrue(output.contains(task.toString()));
    }

    @Test
    void Given_Description_When_PrintByDescription_Should_PrintMatchingTasks() {
        Task matchingTask = new Todo("matching description");
        Task nonMatchingTask = new Todo("other description");

        TaskList.add(matchingTask);
        TaskList.add(nonMatchingTask);

        TaskList.printByDescriptionContains("matching");

        String output = outputStream.toString();
        assertTrue(output.contains("Here are the matching tasks in your list:"));
        assertTrue(output.contains("1. " + matchingTask));
        assertFalse(output.contains("2. " + nonMatchingTask));
    }

    @Test
    void Given_Date_When_PrintByDate_Should_PrintMatchingTasks() {
        Task deadline = new Deadline("deadline task", startDT);
        Task event = new Event("event task", startDT, endDT);
        Task todo = new Todo("irrelevant task");

        TaskList.add(deadline);
        TaskList.add(event);
        TaskList.add(todo);

        TaskList.print(startDT);

        String output = outputStream.toString();
        assertTrue(output.contains("Here are the deadlines and events in your list on:"));
        assertTrue(output.contains(deadline.toString()));
        assertTrue(output.contains(event.toString()));
        assertFalse(output.contains(todo.toString()));
    }
}

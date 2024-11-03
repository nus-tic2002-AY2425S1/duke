package seedu.starkchatbot;

import org.junit.jupiter.api.Test;
import starkchatbot.taskmanager.Task;
import starkchatbot.taskmanager.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTasksTest {
    ArrayList<Task> tasks = new ArrayList<>();
    TaskList taskLists = new TaskList(this.tasks);
    @Test
    public void addTodoTest(){
       taskLists.addTask("Todo", "read books");
        assertEquals("[T] [ ] read books", taskLists.getTasks().get(0).printTask());

    }

    @Test
    public void addDeadlineTest(){
        taskLists.addTask("Deadline", "read books /by 2025-01-10 1600");
        assertEquals("[D] [ ] read books (by: January 10 2025 04:00 pm)", taskLists.getTasks().get(0).printTask());
        assertEquals("read books (by: 2025-01-10 1600)", taskLists.getTasks().get(0).getDescription());
    }

    @Test
    public void addEventTest(){
        taskLists.addTask("Event", "read books /from 2024-11-20 1030 /to 2021-12-25");
        String expectedOutput = "[E] [ ] read books (from: November 20 2024 10:30 am to: December 25 2021)";
        String expectedOutput1 = "read books (from: 2024-11-20 1030 to: 2021-12-25)";

        assertEquals(expectedOutput, taskLists.getTasks().get(0).printTask() );
        assertEquals(expectedOutput1, taskLists.getTasks().get(0).getDescription() );
    }

    @Test
    public void markTaskTest(){
        taskLists.addTask("Todo", "Buy food");
        taskLists.editTask("mark",1);
        assertEquals("[T] [X] Buy food", taskLists.getTasks().get(0).printTask());
    }

    @Test
    public void deleteTaskTest(){
        taskLists.addTask("Todo", "Buy food");      // add 1st task
        taskLists.addTask("Todo", "Read Book");     // add 2nd task
        assertEquals(2, taskLists.getTasks().size());

        taskLists.editTask("delete",1);          // delete 1st task
        assertEquals(1, taskLists.getTasks().size());
    }
}

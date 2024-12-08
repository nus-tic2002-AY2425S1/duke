package pistamint.parser;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import pistamint.general.Deadline;
import pistamint.general.Event;
import pistamint.general.Task;
import pistamint.storage.Storage;
import pistamint.taskList.TaskList;
import pistamint.ui.Ui;

public class ParserTest {


    @Test
    public void parseTodoTaskTest() {
        String taskTodoData="T|0|Todo Test";
        Parser.parseTask(taskTodoData);
        assertEquals("Todo Test",TaskList.tasks.get(TaskList.getSize()-1).getDescription());
        assertFalse(TaskList.tasks.get(TaskList.getSize() - 1).isDone());
        assertEquals('T',TaskList.tasks.get(TaskList.getSize()-1).getSymbol());
    }

    @Test
    public void parseTodoTaskTest_missingDataParts() {
        String taskTodoData="T|0";
        Throwable exception=assertThrows(AssertionError.class,() ->
                Parser.parseTask(taskTodoData));
        assertEquals("Task data format is incorrect. Expected 3 parts but got 2",exception.getMessage());
    }
    @Test
    public void parseDeadlineTaskTest() {
        String taskDeadlinedata = "D|1|Deadline Test (by:Nov 17 2024)";
        Parser.parseTask(taskDeadlinedata);
        assertEquals("Deadline Test ", ((Deadline) TaskList.tasks.get(TaskList.getSize() - 1)).getOnlyDescription());
        assertTrue(TaskList.tasks.get(TaskList.getSize() - 1).isDone());
        assertEquals('D', TaskList.tasks.get(TaskList.getSize() - 1).getSymbol());
        assertEquals("(by:Nov 17 2024)", ((Deadline) TaskList.tasks.get(TaskList.getSize() - 1)).getDeadline());
    }

    @Test
    public void parseDeadlineTaskTest_missingDataParts() {
        String taskDeadlinedata="D|Deadline Test ";
        Throwable exception=assertThrows(AssertionError.class,() ->
                Parser.parseTask(taskDeadlinedata));
        assertEquals("Task data format is incorrect. Expected 3 parts but got 2",exception.getMessage());
    }

    @Test
    public void parseEventTaskTest() {
        String taskEventData = "E|1|Event Test (from:Aug 12 2024 to:Aug 18 2024)";
        Parser.parseTask(taskEventData);
        assertEquals("Event Test ", ((Event)TaskList.tasks.get(TaskList.getSize()-1)).getOnlyDescription());
        assertTrue(TaskList.tasks.get(TaskList.getSize() - 1).isDone());
        assertEquals('E', TaskList.tasks.get(TaskList.getSize()-1).getSymbol());
        assertEquals("(from:Aug 12 2024 ", ((Event) TaskList.tasks.get(TaskList.getSize() - 1)).getFrom());
        assertEquals("to:Aug 18 2024)", ((Event) TaskList.tasks.get(TaskList.getSize() - 1)).getTo());
    }

    @Test
    public void parseEventTaskTest_missingDataParts() {
        String taskEventData="E|1 ";
        Throwable exception=assertThrows(AssertionError.class,() ->
                Parser.parseTask(taskEventData));
        assertEquals("Task data format is incorrect. Expected 3 parts but got 2",exception.getMessage());
    }

    @Test
    public void parseUserInputTodoTaskTest(){
        String input="todo Task Testing";
        String task="todo";

        try (var mock = Mockito.mockStatic(Storage.class)) {
            boolean result = Parser.processTask(input,task);
            // Verify that Storage.appendToFile was called once with the task
            mock.verify(() -> Storage.appendToFile(any(Task.class)), times(1));
            assertTrue(result);
        }
    }
    @Test
    public void parseUserInputTodoTaskTest_emptyInput(){
        String input="todo ";
        String task="todo";
        boolean result = Parser.processTask(input,task);
        assertFalse(result);
    }
    @Test
    public void parseUserInputDeadlineTaskTest(){
        String input="deadline test deadline /by 2024-10-12";
        String task="deadline";

        try (var mock = Mockito.mockStatic(Storage.class)) {
            boolean result = Parser.processTask(input,task);
            // Verify that Storage.appendToFile was called once with the task
            mock.verify(() -> Storage.appendToFile(any(Task.class)), times(1));
            assertTrue(result);
        }
    }
    @Test
    public void parseUserInputDeadlineTaskTest_emptyInput(){
        String input="deadline ";
        String task="deadline";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;  // Save the original System.out
        System.setOut(new PrintStream(baos));
        boolean result=Parser.processTask(input,task);
        String output = baos.toString().replaceAll("[\n\r]+$", "");
        assertFalse(result);
        assertEquals((Ui.start + "\n\tOOPS!!! The description of " + task + " is incomplete." + Ui.end),output);
        System.setOut(originalSystemOut);
    }
    @Test
    public void parseUserInputDeadlineTaskTest_InvalidDateInput() {
        String input="deadline test deadline /by 2024-16-12 ";
        String task="deadline";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;  // Save the original System.out
        System.setOut(new PrintStream(baos));
        boolean result=Parser.processTask(input,task);
        String output = baos.toString().replaceAll("[\n\r]+$", "");
        assertFalse(result);
        assertEquals((Ui.start + "\n\tThe date format you have keyed in is invalid. Please key in the following format 'yyyy-MM-dd'" + Ui.end),output);
        System.setOut(originalSystemOut);
    }

}

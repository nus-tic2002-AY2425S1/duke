package snitch.command;

import org.junit.jupiter.api.Test;
import snitch.task.TaskList;
import snitch.task.Task;
import snitch.task.Todo;
import snitch.task.Deadline;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;


import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    @Test
    public void execute_addTodo_success() throws SnitchException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testdata/snitch_test.txt");
        Command addCommand = new AddCommand("todo Test Todo");

        addCommand.execute(taskList, ui, storage);

        Task addedTask = taskList.get(0);
        assertEquals(1, taskList.size());
        assertTrue(addedTask instanceof Todo);
        assertEquals("Test Todo", addedTask.getDescription());
    }

    @Test
    public void execute_addDeadline_success() throws SnitchException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testdata/snitch_test.txt");
        Command addCommand = new AddCommand("deadline Submit Report /by 2/12/2023 1800");

        addCommand.execute(taskList, ui, storage);

        Task addedTask = taskList.get(0);
        assertEquals(1, taskList.size());
        assertTrue(addedTask instanceof Deadline);
        assertEquals("Submit Report", addedTask.getDescription());
        assertEquals("Dec 02 2023, 6:00 pm", ((Deadline) addedTask).getBy());
    }

    @Test
    public void execute_addInvalidCommand_throwsException() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testdata/snitch_test.txt");

        Command addCommand = new AddCommand("invalidCommand");
        assertThrows(SnitchException.class, () -> addCommand.execute(taskList, ui, storage));
    }
}
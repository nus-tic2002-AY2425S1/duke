package snitch.command;

import org.junit.jupiter.api.Test;
import snitch.task.TaskList;
import snitch.task.Task;
import snitch.task.Todo;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {

    @Test
    public void execute_deleteValidTask_success() throws SnitchException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testdata/snitch_test.txt");
        Task todo = new Todo("Test Todo");
        taskList.add(todo);

        Command deleteCommand = new DeleteCommand(1); // Task index starts from 1
        deleteCommand.execute(taskList, ui, storage);

        assertTrue(taskList.isEmpty());
    }

    @Test
    public void execute_deleteInvalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testdata/snitch_test.txt");

        Command deleteCommand = new DeleteCommand(1); // No task at index 1
        assertThrows(AssertionError.class, () -> deleteCommand.execute(taskList, ui, storage));
    }
}
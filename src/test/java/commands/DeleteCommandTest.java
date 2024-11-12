package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class DeleteCommandTest {

    private DeleteCommand deleteCommand;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() throws StorageOperationException {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    @Test
    public void constructor_validTaskNumber_createsDeleteCommand() {
        deleteCommand = new DeleteCommand(1);
        assertNotNull(deleteCommand);
        assertEquals(1, deleteCommand.getTaskNumber());
    }

    @Test
    public void execute_invalidTaskNumber_throwsCommandException() {
        deleteCommand = new DeleteCommand(99999);

        // Referenced from https://howtodoinjava.com/junit5/expected-exception-example/
        CommandException exception = assertThrows(CommandException.class, () -> deleteCommand.execute(taskList, ui, storage), "CommandException expected");
        assertEquals("The task list is empty. Please add a task first.", exception.getMessage());
//        assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());
        
    }

}

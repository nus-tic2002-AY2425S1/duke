package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import commands.add.EventCommand;
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

    // @Test
    // public void execute_invalidTask_Number_throwsCommandException() {
    //     deleteCommand = new DeleteCommand(99999);
    //     CommandException exception = assertThrows(CommandException.class, () -> {
    //         deleteCommand.execute(taskList, ui, storage);
    //     });
    //     assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());
    // }

    @Test
    public void execute_invalidTaskNumber_throwsCommandException() {
        deleteCommand = new DeleteCommand(99999);

        // Referenced from https://howtodoinjava.com/junit5/expected-exception-example/
        CommandException exception = assertThrows(CommandException.class, () -> {
            deleteCommand.execute(taskList, ui, storage);
        }, "CommandException expected");
        assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());
        

        // // Simulate a scenario where the task number is invalid. Test the logic in execute
        // CommandException exception = assertThrows(CommandException.class, () -> {
        //     throw new CommandException(Messages.ERROR_TASK_NONEXISTENT); // Simulating the expected exception
        // });

        // assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());

        // exception = assertThrows(DukeException.class, () -> new EventCommand("event project meeting /from 2023-10-05 to 2023-10-06"));
        // assertEquals(ErrorMessages.INVALID_EVENT_COMMAND_FORMAT, exception.getMessage());


        // CommandException exception = assertThrows(CommandException.class, () -> deleteCommand.execute(taskList, ui, storage));
        // assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());


        // try {
        //     deleteCommand.execute(taskList, ui, storage);
        //     fail();
        // } catch (CommandException exception) {
        //     assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());
        // }

    }

}

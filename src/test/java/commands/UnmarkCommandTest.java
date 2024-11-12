package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;
import util.TypicalTasks;

public class UnmarkCommandTest {

    private UnmarkCommand unmarkCommand;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private TypicalTasks typicalTasks;

    @BeforeEach
    public void setUp() throws StorageOperationException {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        typicalTasks = new TypicalTasks();
    }

    @Test
    public void constructor_validTaskNumber_createsUnmarkCommand() {
        unmarkCommand = new UnmarkCommand(1);
        assertNotNull(unmarkCommand);
        assertEquals(1, unmarkCommand.getTaskNumber());
    }

    public TaskList initTypicalTasks() {
        return typicalTasks.initDefaultTaskList();
    }

    @Test
    public void execute_emptyTaskList_throwsCommandException() {
        unmarkCommand = new UnmarkCommand(0);
        assertEquals(0, taskList.getSize());
        CommandException exception = assertThrows(CommandException.class, () -> unmarkCommand.execute(taskList, ui, storage), "CommandException expected");
        assertEquals(Messages.MESSAGE_EMPTY_TASKLIST, exception.getMessage());
    }

    @Test
    public void execute_invalidTaskNumber_throwsCommandException() {
        taskList = initTypicalTasks();
        unmarkCommand = new UnmarkCommand(99999);

        // Referenced from https://howtodoinjava.com/junit5/expected-exception-example/
        CommandException exception = assertThrows(CommandException.class, () -> unmarkCommand.execute(taskList, ui, storage), "CommandException expected");
        assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());
    }

    @Test
    public void execute_taskAlreadyUnmarked_doesNotChangeIsDone() {
        taskList = initTypicalTasks();
        
        Todo sampleTodo = typicalTasks.todo_buyGroceries;
        assertFalse(sampleTodo.getIsDone());

        assertFalse(taskList.unmarkTask(sampleTodo));
    }

    @Test
    public void execute_taskSuccessfullyMarked_showsSuccessMessage() 
        throws CommandException, StorageOperationException {
        taskList = initTypicalTasks();
        
        Todo sampleTodo = typicalTasks.todo_doHomework;       // Index 1
        assertTrue(sampleTodo.getIsDone(), "The task should initially be marked as done");

        // Task number is 2 because taskNumber = index of task + 1. User doesn't know that the list is 0-based index
        unmarkCommand = new UnmarkCommand(1);
        unmarkCommand.execute(taskList, ui, storage);
        assertFalse(sampleTodo.getIsDone(), 
            "After executing the unmark command, the task should be marked as not done");

    }

}

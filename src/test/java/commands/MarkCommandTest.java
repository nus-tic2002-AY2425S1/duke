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

public class MarkCommandTest {
    
    private MarkCommand markCommand;
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
    public void constructor_validTaskNumber_createsMarkCommand() {
        markCommand = new MarkCommand(1);
        assertNotNull(markCommand);
        assertEquals(1, markCommand.getTaskNumber());
    }

    public TaskList initTypicalTasks() {
        return typicalTasks.initDefaultTaskList();
    }

    @Test
    public void execute_emptyTaskList_throwsCommandException() {
        markCommand = new MarkCommand(0);
        assertTrue(taskList.getSize() == 0);
        CommandException exception = assertThrows(CommandException.class, () -> {
            markCommand.execute(taskList, ui, storage);
        }, "CommandException expected");
        assertEquals(Messages.MESSAGE_EMPTY_TASKLIST, exception.getMessage());
    }

    @Test
    public void execute_invalidTaskNumber_throwsCommandException() {
        taskList = initTypicalTasks();
        markCommand = new MarkCommand(99999);

        // Referenced from https://howtodoinjava.com/junit5/expected-exception-example/
        CommandException exception = assertThrows(CommandException.class, () -> {
            markCommand.execute(taskList, ui, storage);
        }, "CommandException expected");
        assertEquals(Messages.ERROR_TASK_NONEXISTENT, exception.getMessage());
    }

    @Test
    public void execute_taskAlreadyMarked_showsAlreadyMarkedMessage() 
        throws CommandException, StorageOperationException {
        taskList = initTypicalTasks();
        
        Todo sampleTodo = typicalTasks.todo_doHomework;
        assertTrue(sampleTodo.getIsDone());

        assertFalse(taskList.markTask(0));
    }

    @Test
    public void execute_taskSuccessfullyMarked_showsSuccessMessage() 
        throws CommandException, StorageOperationException {
        taskList = initTypicalTasks();
        
        Todo sampleTodo = typicalTasks.todo_buyGroceries;       // Index 1
        assertFalse(sampleTodo.getIsDone(), "The task should initially be marked as not done");

        // Task number is 2 because taskNumber = index of task + 1. User doesn't know that the list is 0-based index
        markCommand = new MarkCommand(2);
        markCommand.execute(taskList, ui, storage);
        assertTrue(sampleTodo.getIsDone(), "After executing the mark command, the task should be marked as done");

    }

}

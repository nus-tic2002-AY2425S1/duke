package commands.add;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.Task;
import task.Todo;

public class TodoCommandTest {
    
    private TodoCommand todoCommand;
    private final String TEST_DESCRIPTION = "This is a sample description of a Todo task.";
    
    // Initialize with a valid description before each test
    @BeforeEach
    public void setUp() {
        todoCommand = new TodoCommand(TEST_DESCRIPTION);
    }

    // Tests that the constructor correctly initializes the TodoCommand with a valid description.
    @Test
    public void constructor_validDescription_createTodoCommand() {
        assertNotNull(todoCommand);

        // Asset that the description is set correctly
        assertEquals(TEST_DESCRIPTION, todoCommand.getDescription());
    }

    // Tests that the createTask() method correctly creates a Todo task with the given description
    @Test
    public void createTodoTask_validDescription() {
        Task task = todoCommand.createTask();

        // Asserts that the task is not null
        assertNotNull(task);

        // Asserts that the task must be a Todo task
        assertTrue(task instanceof Todo);

        // Asset that the description is set correctly
        constructor_validDescription_createTodoCommand();
        // assertEquals("This is a sample description of a Todo task.", todoCommand.getDescription());
    }

    // Tests that passing a null description to the constructor throws an IllegalArgumentException
    // @Test
    // public void constructor_nullDescription_throwsException() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new TodoCommand(null);
    //     });
    //     assertEquals("Description cannot be null", exception.getMessage());
    // }

    // Tests that passing an empty string as a description throws an IllegalArgumentException
    // @Test
    // public void constructor_emptyDescription_throwsException() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new TodoCommand("");
    //     });
    //     assertEquals("Description cannot be empty", exception.getMessage());
    // }

    // Tests that creating a task with an empty description does not throw an exception and that the task is created with an empty description
    @Test
    public void createTask_emptyDescription_createsTodoTaskWithEmptyDescription() {
        todoCommand = new TodoCommand("");
        Task task = todoCommand.createTask();
        assertNotNull(task);
        assertTrue(task instanceof Todo);
        assertEquals("", task.getDescription());
    }

    // Tests that calling createTask() with a null description throws an IllegalArgumentException
    // @Test
    // public void createTask_nullDescription_throwsException() {
    //     todoCommand = new TodoCommand(null);
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         todoCommand.createTask();
    //     });
    //     assertEquals("Description cannot be null", exception.getMessage());
    // }

}

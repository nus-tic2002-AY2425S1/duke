package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import common.Constants;
import common.Messages;
import exception.FileContentException;
import task.Event;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.Todo;

public class TaskListDecoderTest {
    
    private List<String> encodedTasks;
    private TaskList decodedTasks;

    @Test
    public void decodeTaskList_validTodo_decodesSuccessfully() throws Exception {
        encodedTasks = Arrays.asList("T | 0 | learn magic");
        decodedTasks = TaskListDecoder.decodeTaskList(encodedTasks);

        assertEquals(1, decodedTasks.getSize());
        Task task = decodedTasks.getTask(0);
        assertTrue(task instanceof Todo);
        assertEquals("learn magic", task.getDescription());
        assertFalse(task.getIsDone());
    }

    @Test
    public void decodeTaskList_validDeadline_decodesSuccessfully() throws Exception {
        encodedTasks = Arrays.asList("D | 0 | prepare for presentation | Oct 10 2024 1000");
        decodedTasks = TaskListDecoder.decodeTaskList(encodedTasks);

        assertEquals(1, decodedTasks.getSize());
        Task task = decodedTasks.getTask(0);
        assertTrue(task instanceof Deadline);
        assertEquals("prepare for presentation", task.getDescription());
        assertFalse(task.getIsDone());
        assertEquals(LocalDateTime.of(2024, 10, 10, 10, 0), ((Deadline) task).getDue());
    }

    @Test
    public void decodeTaskList_validEvent_decodesSuccessfully() throws Exception {
        encodedTasks = Arrays.asList("E | 0 | networking lunch | Sep 11 2024 1200 | Sep 11 2024 1400");
        decodedTasks = TaskListDecoder.decodeTaskList(encodedTasks);

        assertEquals(1, decodedTasks.getSize());
        Task task = decodedTasks.getTask(0);
        assertTrue(task instanceof Event);
        assertEquals("networking lunch", task.getDescription());
        assertFalse(task.getIsDone());
        assertEquals(LocalDateTime.of(2024, 9, 11, 12, 0), 
            ((Event) task).getStartDateTime());
        assertEquals(LocalDateTime.of(2024, 9, 11, 14, 0), 
            ((Event) task).getEndDateTime());
    }

    @Test
    public void decodeTaskList_emptyTask_throwsException() {
        encodedTasks = Arrays.asList(Constants.EMPTY_STRING);
        FileContentException exception = assertThrows(FileContentException.class, () -> {
            TaskListDecoder.decodeTaskList(encodedTasks);
        });

        assertEquals(
            String.format("%s. %s.", Messages.MESSAGE_EMPTY_LINE, Messages.MESSAGE_INVALID_TASKS_DATA), 
            exception.getMessage()
        );
    }

    @Test
    public void decodeTaskList_invalidFormat_throwsException() {
        List<String> encodedTasks = Arrays.asList("T | 0");
        Exception exception = assertThrows(FileContentException.class, () -> {
            TaskListDecoder.decodeTaskList(encodedTasks);
        });

        assertEquals(
            String.format("%s. %s.", Messages.MESSAGE_TASK_MISSING_COMPONENTS, Messages.MESSAGE_INVALID_TASKS_DATA),
            exception.getMessage()
        );
    }

    @Test
    public void decodeTaskList_unknownTaskType_throwsException() {
        List<String> encodedTasks = Arrays.asList("X | 0 | unknown task");
        Exception exception = assertThrows(FileContentException.class, () -> {
            TaskListDecoder.decodeTaskList(encodedTasks);
        });

        assertEquals(
            "Error: Unknown task type.",
            exception.getMessage()
        );
    }

    @Test
    public void decodeTaskList_invalidCompletionStatus_throwsException() {
        List<String> encodedTasks = Arrays.asList("T | 2 | invalid status task");
        Exception exception = assertThrows(FileContentException.class, () -> {
            TaskListDecoder.decodeTaskList(encodedTasks);
        });

        assertEquals(
            "Task has invalid completion status",
            exception.getMessage()
        );
    }

}

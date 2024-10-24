package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    void testConstructor() {
        String description = "Submit assignment";

        ToDo todo = new ToDo(description);

        assertEquals(description, todo.getDescription());
    }

    @Test
    void testToString() {
        String description = "Submit assignment";
        ToDo todo = new ToDo(description);

        String expectedString = "[T][ ] Submit assignment";
        assertEquals(expectedString, todo.toString());
    }


    @Test
    void testToFileFormat() {
        String description = "Submit assignment";
        ToDo todo = new ToDo(description);

        String expectedFileFormat = "T|0|Submit assignment";
        assertEquals(expectedFileFormat, todo.toFileFormat());
    }

}
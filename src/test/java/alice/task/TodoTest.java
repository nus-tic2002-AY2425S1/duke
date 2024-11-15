package alice.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void markInitialization() throws Exception {
        //string should be converted to save file
        assertEquals("T false return book",new Todo("return book").toSave());

    }

}
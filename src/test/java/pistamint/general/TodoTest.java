package pistamint.general;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Task todoTest=new Todo("Testing todo",'T');

    @Test
    public void getDescriptionTest(){
        assertEquals("Testing todo",todoTest.getDescription());
    }

    @Test
    public void setDescriptionTest(){
        todoTest.setDescription("New Testing todo");
        assertEquals("New Testing todo",todoTest.getDescription());
    }

    @Test
    public void isDoneTest(){
        assertEquals(false,todoTest.isDone());
    }

    @Test
    public void markDoneTest(){
        todoTest.markAsDone();
        assertEquals(true,todoTest.isDone());
    }

    @Test
    public void markUndoneTest(){
        todoTest.markAsUnDone();
        assertEquals(false,todoTest.isDone());
    }



}

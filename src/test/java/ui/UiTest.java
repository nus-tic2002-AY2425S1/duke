package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import commands.Command;
import exception.CommandException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UiTest {
    
    private final Ui ui = new Ui();
    private final String FOUR_SPACES = "    ";
    private final String FIVE_SPACES = "     ";
    private final String SEVEN_SPACES = "       ";
    private final String NO_SPACE = "";
    
    private final String EMPTY_INPUT = NO_SPACE;

    @Test
    public void formatSpace() {
        assertEquals(FOUR_SPACES, ui.formatSpace(4));
        assertEquals(SEVEN_SPACES, ui.formatSpace(7));
        assertEquals(FIVE_SPACES, ui.formatSpace(5));
    }

    @Test
    public void getSpace() {
        assertEquals(FOUR_SPACES, ui.getSpace(true, false));        // Horizontal line
        assertEquals(SEVEN_SPACES, ui.getSpace(false, true));       // Task
        assertEquals(FIVE_SPACES, ui.getSpace(false, false));       // General space
        assertEquals(NO_SPACE, ui.getSpace(true, true));
    }

    @Test
    public void getLine() {
        String LINE = "____________________________________________________________";
        assertEquals(FOUR_SPACES + LINE, ui.getLine());
    }

    // @Test
    // public void printMessage_array() {

    // }

    // @Test
    // public void printMessage_arrayList() {
    //     ArrayList<String> messageList = new ArrayList<>();

    //     messageList.add("This ");
    //     messageList.add("is ");
    //     messageList.add("a ");
    //     messageList.add("sample ");
    //     messageList.add("message ");
    //     messageList.add("list ");
    //     messageList.add("of ");
    //     messageList.add("ArrayList ");
    //     messageList.add("type.");
    // }

    // @Test
    // public void readInput() throws CommandException {
    //     String TEST_USER_INPUT = "Test User Input";

    //     try {
    //         String result = ui.readInput();
    //         assertEquals(TEST_USER_INPUT, result);
    //     } catch (CommandException e) {
    //         fail("Should not throw CommandException: " + e);
    //     }
    // }
}

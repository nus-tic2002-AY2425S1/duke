package seedu.starkchatbot;

import org.junit.jupiter.api.Test;
import starkchatbot.userui.StarkException;
import starkchatbot.userui.Tokenize;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTokenize {

    @Test
    public void testTokenizeTodoInput() {
        String[] output = Tokenize.tokenize("todo read book");
        String[] input = new String[]{"todo", "read", "book"};

        assertArrayEquals(input, output);
    }

    @Test
    public void testTokenizeEventInput() {
        String[] output = Tokenize.tokenize("event read book /from mon 2pm /to sat 3pm");
        String[] input = new String[]{"event", "read", "book", "/from", "mon", "2pm", "/to", "sat", "3pm"};

        assertArrayEquals(input, output);
    }

    @Test
    public void testTokenizeEventIncorrectInput() {

        assertThrows(StarkException.InvalidDescriptionException.class, () -> {
            String[] output = Tokenize.tokenize("event read book /from mon 2pm ");
        });
    }

    @Test
    public void testTokenizeException1() {
        assertThrows(StarkException.InvalidDescriptionException.class, () -> {
            String[] output = Tokenize.tokenize("deadline read book");
        });

    }

    @Test
    public void testTokenizeException2() {
        assertThrows(StarkException.InvalidCommandException.class, () -> {
            String[] output = Tokenize.tokenize("read book");
        });

    }

}

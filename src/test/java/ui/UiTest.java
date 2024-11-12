package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import common.Constants;
import org.junit.jupiter.api.Test;

public class UiTest {
    
    private final Ui ui = new Ui();
    private final String FOUR_SPACES = "    ";
    private final String FIVE_SPACES = "     ";
    private final String TWO_SPACES = "  ";

    @Test
    public void formatSpace() {
        assertEquals(FOUR_SPACES, ui.formatSpace(Constants.FOUR));
        assertEquals(TWO_SPACES, ui.formatSpace(Constants.TWO));
        assertEquals(FIVE_SPACES, ui.formatSpace(Constants.FIVE));
    }

    @Test
    public void getSpace() {
        assertEquals(FOUR_SPACES, ui.getSpace(true, false));        // Horizontal line
        assertEquals(TWO_SPACES, ui.getSpace(false, true));         // Task
        assertEquals(FIVE_SPACES, ui.getSpace(false, false));       // General space
        String NO_SPACE = Constants.EMPTY_STRING;
        assertEquals(NO_SPACE, ui.getSpace(true, true));
    }

    @Test
    public void getLine() {
        String LINE = "____________________________________________________________";
        assertEquals(FOUR_SPACES + LINE, ui.getLine());
    }

}

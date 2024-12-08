package denny.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        ui = new Ui();
    }

    @Test
    void testShowLine() {
        ui.showLine();
        String expectedLine = "____________________________________________________________\n";
        String actualOutput = outputStream.toString().replace(System.lineSeparator(), "\n");
        assertEquals(expectedLine, actualOutput);
    }

}
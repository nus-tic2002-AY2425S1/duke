package mochi;

import mochi.common.DialogMessages;
import mochi.storage.SaveManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MochiTest {
  private final InputStream originalSystemIn = System.in;
  private final PrintStream originalSystemOut = System.out;
  private ByteArrayOutputStream testOutput;

  @BeforeEach
  void setUpStreams() {
    testOutput = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOutput));
  }
  @BeforeEach
  void setUp() throws IOException {
    SaveManager saveManager = new SaveManager();
    saveManager.deleteData();
  }

  @Test
  void testMainWithTodoCommand() {
    String simulatedInput = "todo Buy milk\nbye\n";
    String output = simulateInput(simulatedInput);
    generalCheck(output);
    // Reset streams
    System.setIn(originalSystemIn);
    System.setOut(originalSystemOut);
  }
  @Test
  void testMainWithEventCommand() {
    String simulatedInput = "Event Buy milk /from 11/11/2024 0000 /to 11/11/2024 0800\nbye\n";
    String output = simulateInput(simulatedInput);
    generalCheck(output);
    assertTrue(output.contains("[E][ ] Buy milk (from: Nov 11 2024, 12:00AM to: Nov 11 2024, 8:00AM)"),"Expected event output does not match actual output.");
    // Reset streams
    System.setIn(originalSystemIn);
    System.setOut(originalSystemOut);
  }

  @Test
  void testMainWithDeadlineCommand() {
    String simulatedInput = "deadline Buy milk /by 11/11/2024 0000\nbye\n";
    String output = simulateInput(simulatedInput);
    generalCheck(output);
    assertTrue(output.contains("[D][ ] Buy milk (by Nov 11 2024, 12:00AM)"),"Expected event output does not match actual output.");
    // Reset streams
    System.setIn(originalSystemIn);
    System.setOut(originalSystemOut);
  }
  @Test
  void testMainWithInvalidCommand() {
    String simulatedInput = "invalidCommand\nbye\n";
    String output = simulateInput(simulatedInput);
    generalCheck(output);
    assertTrue(output.contains("wrong input"), "Should contain an error message for invalid command");
    // Reset streams
    System.setIn(originalSystemIn);
    System.setOut(originalSystemOut);
  }

  private void checkGreetings(String output) {
    assertTrue(output.contains(DialogMessages.GREETINGS.getValue()), "Should contain greeting message");
  }

  private void checkBye(String output) {
    assertTrue(output.contains("Bye"), "Should contain 'Bye' indicating exit command processed");
  }
  private void checkError(String output) {
    assertFalse(output.contains("Error"), "Output should not contain unexpected 'Error' message");
  }
  private void generalCheck(String output) {
    checkGreetings(output);
    checkBye(output);
    checkError(output);
  }

  private String simulateInput(String input) {
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Mochi.main(new String[]{});
    return testOutput.toString();
  }
}

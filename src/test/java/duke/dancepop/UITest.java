package duke.dancepop;

import duke.dancepop.enums.CommandEnum;
import duke.dancepop.exceptions.ErrorMessageBuilder;
import duke.dancepop.exceptions.ExceptionConsts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UITest {
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setup() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    void Given_EmptyAndByeCommand_When_Start_Should_DisplayHelloAndUnknownAndByeMessage() {
        setInput("");

        UI.start();

        String output = outputStream.toString();
        assertTrue(output.contains("Hello! I'm DancePop"));
        assertTrue(output.contains("What can I do for you?"));
        assertTrue(output.contains(ExceptionConsts.UNKNOWN_COMMAND_ERROR));
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @ParameterizedTest()
    @MethodSource("getInvalidIndexCommand")
    void Given_InvalidIndexCommandAndNoTask_When_Start_Should_DisplayInvalidIndexMessage(String command) {
        setInput(command);

        UI.start();

        String output = outputStream.toString();
        assertTrue(output.contains(ExceptionConsts.INDEX_INVALID_ERROR));
    }

    @ParameterizedTest()
    @MethodSource("getMissingIndexCommand")
    void Given_MissingIndexCommand_When_Start_Should_DisplayMissingIndexMessage(String command, List<String> errors) {
        setInput(command);

        UI.start();

        String output = outputStream.toString();
        for (String error: errors) {
            assertTrue(output.contains(error));
        }
    }


    @ParameterizedTest()
    @MethodSource("getInvalidIntegerCommand")
    void Given_InvalidIntegerCommand_When_Start_Should_DisplayInvalidIntegerMessage(String command, List<String> errors) {
        setInput(command);

        UI.start();

        String output = outputStream.toString();
        for (String error: errors) {
            assertTrue(output.contains(error));
        }
    }

    @ParameterizedTest()
    @MethodSource("getInvalidTodoCommand")
    void Given_InvalidTodoCommand_When_Start_Should_DisplayInvalidTodoMessage(String command, List<String> errors) {
        setInput(command);

        UI.start();

        String output = outputStream.toString();
        for (String error: errors) {
            assertTrue(output.contains(error));
        }
    }

    @ParameterizedTest()
    @MethodSource("getInvalidDeadlineCommand")
    void Given_InvalidDeadlineCommand_When_Start_Should_DisplayInvalidDeadlineMessage(String command, List<String> errors) {
        setInput(command);

        UI.start();

        String output = outputStream.toString();
        for (String error: errors) {
            assertTrue(output.contains(error));
        }
    }

    @ParameterizedTest()
    @MethodSource("getInvalidEventCommand")
    void Given_InvalidEventCommand_When_Start_Should_DisplayInvalidEventMessage(String command, List<String> errors) {
        setInput(command);

        UI.start();

        String output = outputStream.toString();
        for (String error: errors) {
            assertTrue(output.contains(error));
        }
    }

    private static Stream<String> getInvalidIndexCommand() {
        return Stream.of("mark 1", "unmark 1", "delete 1");
    }

    private static Stream<Arguments> getMissingIndexCommand() {
        return Stream.of(
                Arguments.of("mark", new ErrorMessageBuilder(CommandEnum.MARK).missingIndex().build()),
                Arguments.of("unmark", new ErrorMessageBuilder(CommandEnum.UNMARK).missingIndex().build()),
                Arguments.of("delete", new ErrorMessageBuilder(CommandEnum.DELETE).missingIndex().build())
        );
    }

    private static Stream<Arguments> getInvalidIntegerCommand() {
        return Stream.of(
                Arguments.of("mark a", new ErrorMessageBuilder(CommandEnum.MARK).integerParse().build()),
                Arguments.of("unmark a", new ErrorMessageBuilder(CommandEnum.UNMARK).integerParse().build()),
                Arguments.of("delete a", new ErrorMessageBuilder(CommandEnum.DELETE).integerParse().build())
        );
    }

    private static Stream<Arguments> getInvalidTodoCommand() {
        CommandEnum command = CommandEnum.TODO;
        return Stream.of(
                Arguments.of("todo", new ErrorMessageBuilder(command).missingDescription().build()),
                Arguments.of("todo123", new ErrorMessageBuilder(command).unknownCommand().build())
        );
    }

    private static Stream<Arguments> getInvalidDeadlineCommand() {
        CommandEnum command = CommandEnum.DEADLINE;
        return Stream.of(
                Arguments.of("deadline123", new ErrorMessageBuilder(command).unknownCommand().build()),
                Arguments.of("deadline/by", new ErrorMessageBuilder(command).unknownCommand().build()),
                Arguments.of("deadline /by", new ErrorMessageBuilder(command).missingBy().build())
        );
    }

    private static Stream<Arguments> getInvalidEventCommand() {
        CommandEnum command = CommandEnum.EVENT;
        return Stream.of(
                Arguments.of("event123", new ErrorMessageBuilder(command).unknownCommand().build()),
                Arguments.of("event/from", new ErrorMessageBuilder(command).unknownCommand().build()),
                Arguments.of("event/to", new ErrorMessageBuilder(command).unknownCommand().build()),
                Arguments.of("event /from /to", new ErrorMessageBuilder(command).missingDescription().missingFrom().missingTo().build()),
                Arguments.of("event", new ErrorMessageBuilder(command).unknownArguments().build()),
                Arguments.of("event /from ", new ErrorMessageBuilder(command).unknownArguments().build()),
                Arguments.of("event /to ", new ErrorMessageBuilder(command).unknownArguments().build())
        );
    }


    private void setInput(String input) {
        // Force the exit of while loop by throwing exception in ByeCommand
        input += "\n" + CommandEnum.BYE.getValue() + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}

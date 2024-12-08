package duke.dancepop.parser;

import duke.dancepop.exceptions.InputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    static Stream<Arguments> validCommandProvider() {
        return Stream.of(
                Arguments.of("todo read book", TodoCommand.class),
                Arguments.of("deadline submit report /by 2024-12-01 1800", DeadlineCommand.class),
                Arguments.of("event conference /from 2024-12-01 0900 /to 2024-12-01 1700", EventCommand.class),
                Arguments.of("mark 1", MarkCommand.class),
                Arguments.of("unmark 1", UnmarkCommand.class),
                Arguments.of("delete 1", DeleteCommand.class),
                Arguments.of("list", ListCommand.class),
                Arguments.of("bye", ByeCommand.class),
                Arguments.of("save tasks.csv", SaveToFileNameCommand.class),
                Arguments.of("load tasks.csv", LoadFromFileNameCommand.class),
                Arguments.of("find book", FindCommand.class)
        );
    }

    static Stream<String> invalidCommandProvider() {
        return Stream.of(
                "unknown command",
                "todo ",
                "deadline /by 2024-12-01",
                "event /from 2024-12-01 0900",
                "mark ",
                "delete",
                "save",
                "load invalidfile.txt"
        );
    }

    static Stream<String> invalidDeadlineInputs() {
        return Stream.of(
                "deadline ",
                "deadline read book /by ",
                "deadline read book"
        );
    }

    static Stream<String> validEventInputs() {
        return Stream.of(
                "event conference /from 2024-12-01 0900 /to 2024-12-01 1700",
                "event meeting /from 2024-11-01 0900 /to 2024-11-01 1200"
        );
    }

    @ParameterizedTest
    @MethodSource("validCommandProvider")
    void Given_ValidCommand_When_Parse_Should_ReturnCorrectCommandObject(String input, Class<Command> expectedCommandClass) throws InputException {
        // Referenced from https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
        Command command = Parser.parse(input);
        assertInstanceOf(expectedCommandClass, command);
    }

    @ParameterizedTest
    @MethodSource("invalidCommandProvider")
    void Given_InvalidCommand_When_Parse_Should_ThrowInputException(String input) {
        assertThrows(InputException.class, () -> Parser.parse(input));
    }

    @Test
    void Given_ValidTodoInput_When_Parse_Should_ReturnTodoCommand() throws InputException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @ParameterizedTest
    @MethodSource("invalidDeadlineInputs")
    void Given_InvalidDeadlineInput_When_Parse_Should_ThrowInputException(String input) {
        assertThrows(InputException.class, () -> Parser.parse(input));
    }

    @ParameterizedTest
    @MethodSource("validEventInputs")
    void Given_ValidEventInput_When_Parse_Should_ReturnEventCommand(String input) throws InputException {
        Command command = Parser.parse(input);
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    void Given_BadFormatSaveCommand_Should_ThrowException() {
        assertThrows(InputException.class, () -> Parser.parse("save wrongfileformat.txt"));
    }

    @Test
    void Given_BadFormatLoadCommand_Should_ThrowException() {
        assertThrows(InputException.class, () -> Parser.parse("load wrongfileformat.txt"));
    }

    @Test
    void Given_ValidFindCommand_Should_ReturnFindCommand() throws InputException {
        Command command = Parser.parse("find keyword");
        assertInstanceOf(FindCommand.class, command);
    }
}

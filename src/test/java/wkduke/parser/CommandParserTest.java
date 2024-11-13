package wkduke.parser;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.command.*;
import wkduke.exception.CommandFormatException;
import wkduke.exception.TaskFormatException;
import wkduke.task.TaskPriority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class CommandParserTest {
    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private static Stream<Object[]> validDeadlineCommandProvider() {
            return Stream.of(
                    new Object[]{"deadline Submit report /by 2024-11-05 23:59",
                            new AddDeadlineCommand("Submit report",
                                    LocalDateTime.of(2024, 11, 5, 23, 59))},
                    new Object[]{"deadline Finish assignment /by 2024/11/06 1800",
                            new AddDeadlineCommand("Finish assignment",
                                    LocalDateTime.of(2024, 11, 6, 18, 0))}
            );
        }

        private static Stream<Object[]> validDeleteCommandProvider() {
            return Stream.of(
                    new Object[]{"delete 1", new DeleteCommand(1)},
                    new Object[]{"delete 42", new DeleteCommand(42)}
            );
        }

        private static Stream<Object[]> validEventCommandProvider() {
            return Stream.of(
                    new Object[]{"event Meeting /from 2024-11-05 09:00 /to 2024-11-05 17:00",
                            new AddEventCommand("Meeting",
                                    LocalDateTime.of(2024, 11, 5, 9, 0),
                                    LocalDateTime.of(2024, 11, 5, 17, 0))},
                    new Object[]{"event Workshop /from 2024/11/06 10:00 /to 2024/11/06 12:00",
                            new AddEventCommand("Workshop",
                                    LocalDateTime.of(2024, 11, 6, 10, 0),
                                    LocalDateTime.of(2024, 11, 6, 12, 0))}
            );
        }

        private static Stream<Object[]> validFindCommandProvider() {
            return Stream.of(
                    new Object[]{"find book", new FindCommand(List.of("book"))},
                    new Object[]{"find report", new FindCommand(List.of("report"))},
                    new Object[]{"find assignment,report", new FindCommand(List.of("assignment", "report"))}
            );
        }

        private static Stream<Object[]> validListCommandProvider() {
            return Stream.of(
                    new Object[]{"list", new ListCommand()},
                    new Object[]{"list /on 2024-11-05",
                            new ListOnCommand(LocalDateTime.of(2024, 11, 5, 0, 0))},
                    new Object[]{"list /on 2024/11/05",
                            new ListOnCommand(LocalDateTime.of(2024, 11, 5, 0, 0))}
            );
        }

        private static Stream<Object[]> validMarkCommandProvider() {
            return Stream.of(
                    new Object[]{"mark 1", new MarkCommand(1)},
                    new Object[]{"mark 42", new MarkCommand(42)}
            );
        }

        private static Stream<Object[]> validToDoCommandProvider() {
            return Stream.of(
                    new Object[]{"todo Read book", new AddToDoCommand("Read book")},
                    new Object[]{"todo Return book", new AddToDoCommand("Return book")}
            );
        }

        private static Stream<Object[]> validUnmarkCommandProvider() {
            return Stream.of(
                    new Object[]{"unmark 1", new UnmarkCommand(1)},
                    new Object[]{"unmark 42", new UnmarkCommand(42)}
            );
        }

        private static Stream<Object[]> validUpdatePriorityCommandProvider() {
            return Stream.of(
                    new Object[]{"update-priority 1 H", new UpdatePriorityCommand(1, TaskPriority.HIGH)},
                    new Object[]{"update-priority 2 M", new UpdatePriorityCommand(2, TaskPriority.MEDIUM)},
                    new Object[]{"update-priority 3 L", new UpdatePriorityCommand(3, TaskPriority.LOW)}
            );
        }

        @Order(1)
        @Test
        public void parseCommand_exitCommand_returnsExitCommand() throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand("bye");
            assertInstanceOf(ExitCommand.class, result, "Expected an instance of ExitCommand");
        }

        @Order(4)
        @ParameterizedTest
        @MethodSource("validDeadlineCommandProvider")
        public void parseCommand_validDeadlineCommands_returnsAddDeadlineCommand(String input, AddDeadlineCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(8)
        @ParameterizedTest
        @MethodSource("validDeleteCommandProvider")
        public void parseCommand_validDeleteCommands_returnsDeleteCommand(String input, DeleteCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(5)
        @ParameterizedTest
        @MethodSource("validEventCommandProvider")
        public void parseCommand_validEventCommands_returnsAddEventCommand(String input, AddEventCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(10)
        @ParameterizedTest
        @MethodSource("validFindCommandProvider")
        public void parseCommand_validFindCommand_returnsFindCommand(String input, FindCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(2)
        @ParameterizedTest
        @MethodSource("validListCommandProvider")
        public void parseCommand_validListCommands_returnsListCommand(String input, Command expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(6)
        @ParameterizedTest
        @MethodSource("validMarkCommandProvider")
        public void parseCommand_validMarkCommands_returnsMarkCommand(String input, MarkCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(3)
        @ParameterizedTest
        @MethodSource("validToDoCommandProvider")
        public void parseCommand_validToDoCommands_returnsAddToDoCommand(String input, AddToDoCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(7)
        @ParameterizedTest
        @MethodSource("validUnmarkCommandProvider")
        public void parseCommand_validUnmarkCommands_returnsUnmarkCommand(String input, UnmarkCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }

        @Order(9)
        @ParameterizedTest
        @MethodSource("validUpdatePriorityCommandProvider")
        public void parseCommand_validUpdatePriorityCommands_returnsUpdatePriorityCommand(String input, UpdatePriorityCommand expected) throws CommandFormatException, TaskFormatException {
            Command result = CommandParser.parseCommand(input);
            assertEquals(expected, result);
        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InvalidTests {
        private static Stream<String> invalidCommandWordProvider() {
            return Stream.of(
                    "invalidCommand",   // Completely invalid command
                    "unknown 123"               // Unknown command with arguments
            );
        }

        private static Stream<String> invalidDeadlineCommandProvider() {
            return Stream.of(
                    "deadline Finish assignment",           // Missing date
                    "deadline Finish assignment /by",               // Missing date
                    "deadline /by 2024-11-05 23:59",                // Missing description
                    "deadline Finish assignment /by invalid-date"   // Invalid date format
            );
        }

        private static Stream<String> invalidDeleteCommandProvider() {
            return Stream.of(
                    "delete",       // Missing task number
                    "delete -1",            // Invalid task number
                    "delete not-a-number"   // Non-integer task number
            );
        }

        private static Stream<String> invalidEventCommandProvider() {
            return Stream.of(
                    "event Meeting /to 2024-11-05 17:00",               // Missing start date time
                    "event Meeting /from 2024-11-05 09:00 /to",                 // Missing end date time
                    "event /from 2024-11-05 09:00 /to 2024-11-05 17:00",        // Missing description
                    "event Meeting /from invalid-date /to 2024-11-05 17:00",    // Invalid start date time
                    "event Meeting /from 2024-11-05 17:00 /to 2024-11-05 09:00" // End time before start time
            );
        }

        private static Stream<String> invalidFindCommandProvider() {
            return Stream.of(
                    "find", // Missing keyword
                    "find ",        // Empty keyword
                    "find ,"        // Only comma
            );
        }

        private static Stream<String> invalidListCommandProvider() {
            return Stream.of(
                    "list /on ",        // Missing arguments
                    "list /on invalid-date",    // Invalid date format
                    "list extra arguments"      // Invalid arguments
            );
        }

        private static Stream<String> invalidMarkCommandProvider() {
            return Stream.of(
                    "mark",     // Missing task number
                    "mark -1",          // Invalid task number
                    "mark not-a-number" // Non-integer task number
            );
        }

        private static Stream<String> invalidToDoCommandProvider() {
            return Stream.of(
                    "todo", // Missing description
                    "todo "         // Empty description
            );
        }

        private static Stream<String> invalidUnmarkCommandProvider() {
            return Stream.of(
                    "unmark",       // Missing task number
                    "unmark -1",            // Invalid task number
                    "unmark not-a-number"   // Non-integer task number
            );
        }

        private static Stream<String> invalidUpdatePriorityCommandProvider() {
            return Stream.of(
                    "update-priority",  // Missing both task number and priority
                    "update-priority 1",        // Missing priority
                    "update-priority one H",    // Non-integer task number
                    "update-priority 1 X",      // Invalid priority letter
                    "update-priority -1 H"      // Invalid task number (negative)
            );
        }

        @Order(8)
        @ParameterizedTest
        @MethodSource("invalidCommandWordProvider")
        public void parseCommand_invalidCommandWord_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(3)
        @ParameterizedTest
        @MethodSource("invalidDeadlineCommandProvider")
        public void parseCommand_invalidDeadlineCommands_throwsTaskFormatException(String input) {
            assertThrows(TaskFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(7)
        @ParameterizedTest
        @MethodSource("invalidDeleteCommandProvider")
        public void parseCommand_invalidDeleteCommands_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(4)
        @ParameterizedTest
        @MethodSource("invalidEventCommandProvider")
        public void parseCommand_invalidEventCommands_throwsTaskFormatException(String input) {
            assertThrows(TaskFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(10)
        @ParameterizedTest
        @MethodSource("invalidFindCommandProvider")
        public void parseCommand_invalidFindCommands_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("invalidListCommandProvider")
        public void parseCommand_invalidListCommands_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(5)
        @ParameterizedTest
        @MethodSource("invalidMarkCommandProvider")
        public void parseCommand_invalidMarkCommands_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(2)
        @ParameterizedTest
        @MethodSource("invalidToDoCommandProvider")
        public void parseCommand_invalidToDoCommands_throwsTaskFormatException(String input) {
            assertThrows(TaskFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(6)
        @ParameterizedTest
        @MethodSource("invalidUnmarkCommandProvider")
        public void parseCommand_invalidUnmarkCommands_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }

        @Order(9)
        @ParameterizedTest
        @MethodSource("invalidUpdatePriorityCommandProvider")
        public void parseCommand_invalidUpdatePriorityCommands_throwsCommandFormatException(String input) {
            assertThrows(CommandFormatException.class, () -> CommandParser.parseCommand(input));
        }
    }
}

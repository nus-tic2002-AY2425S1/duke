package wkduke.parser;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.exception.TaskFormatException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Solution below inspired by https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-execution-order
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TimeParserTest {
    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private static Stream<Object[]> boundaryTimeProvider() {
            LocalDateTime date = LocalDateTime.of(2024, 11, 5, 0, 0);
            return Stream.of(
                    new Object[]{"2024-11-05 00:00", date.withHour(0).withMinute(0)}, // Midnight
                    new Object[]{"2024-11-05 23:59", date.withHour(23).withMinute(59)} // End of day
            );
        }

        private static Stream<Object[]> validDateTimeProvider() {
            LocalDateTime expectedDateTime1 = LocalDateTime.of(2024, 11, 5, 0, 0);
            LocalDateTime expectedDateTime2 = LocalDateTime.of(2024, 11, 5, 18, 30);
            return Stream.of(
                    new Object[]{"2024/11/05", expectedDateTime1},          // Format: yyyy/MM/dd
                    new Object[]{"2024-11-05", expectedDateTime1},          // Format: yyyy-MM-dd
                    new Object[]{"2024/11/05 1830", expectedDateTime2},     // Format: yyyy/MM/dd HHmm
                    new Object[]{"2024-11-05 18:30", expectedDateTime2}     // Format: yyyy-MM-dd HH:mm
            );
        }

        @Order(2)
        @ParameterizedTest
        @MethodSource("boundaryTimeProvider")
        public void parseDateTime_boundaryTimes_returnsLocalDateTime(String input, LocalDateTime expected) throws TaskFormatException {
            assertEquals(expected, TimeParser.parseDateTime(input));
        }


        @Order(1)
        @ParameterizedTest
        @MethodSource("validDateTimeProvider")
        public void parseDateTime_validDateFormats_returnsLocalDateTime(String input, LocalDateTime expected) throws TaskFormatException {
            assertEquals(expected, TimeParser.parseDateTime(input));
        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InvalidTests {
        private static Stream<String> extraneousCharacterProvider() {
            return Stream.of(
                    " 2024/11/05 ",     // Leading and trailing whitespace
                    "2024/11/05abc",            // Extra characters after date
                    "abc2024/11/05"             // Extra characters before date
            );
        }

        private static Stream<String> invalidDateTimeProvider() {
            return Stream.of(
                    "05-11-2024",   // Unsupported format
                    "2024/11/05 6:30PM",    // Unsupported format
                    "2024-13-05",           // Invalid month
                    "2024/02/40",           // Invalid day
                    "2024-11-05 25:00",     // Invalid hour
                    "2024-11-05 23:60",     // Invalid minute
                    "Invalid String",       // Invalid string
                    ""                      // Empty String
            );
        }

        @Order(2)
        @ParameterizedTest
        @MethodSource("extraneousCharacterProvider")
        public void parseDateTime_extraneousCharacters_throwsTaskFormatException(String input) {
            assertThrows(TaskFormatException.class, () -> TimeParser.parseDateTime(input));
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("invalidDateTimeProvider")
        public void parseDateTime_invalidDateFormats_throwsTaskFormatException(String input) {
            assertThrows(TaskFormatException.class, () -> TimeParser.parseDateTime(input));
        }
    }
}
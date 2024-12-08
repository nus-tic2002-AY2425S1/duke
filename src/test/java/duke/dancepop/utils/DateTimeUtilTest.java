package duke.dancepop.utils;

import duke.dancepop.exceptions.InputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeUtilTest {

    private static Stream<Arguments> getValidDateTime() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2024, 1, 2, 14, 30), "2024-01-02 14:30"),
                Arguments.of(LocalDateTime.of(2024, 1, 2, 14, 30), "2024-01-02 1430"),
                Arguments.of(LocalDateTime.of(2024, 1, 2, 0, 0), "2024-01-02"));
    }

    private static Stream<Arguments> getInvalidDateTime() {
        return Stream.of(
                Arguments.of("invalid datetime"),
                Arguments.of("invalid 18:00"),
                Arguments.of("invalid 1800"),
                Arguments.of("2024-01-01 invalid"),
                Arguments.of("2024-01-01 invalid"),
                Arguments.of("2024-01-01 18:60"),
                Arguments.of("2024-01-01 25:00"),
                Arguments.of("2024-1-01 18:60"),
                Arguments.of("2024-1-01 1800"),
                Arguments.of("2024-01-1 18:60"),
                Arguments.of("2024-01-1 1800"),
                Arguments.of("2024--01-1 18:60"),
                Arguments.of("2024--01-1 1800"),
                Arguments.of("2024-01-01 1860"),
                Arguments.of("2024-01-01 2500"),
                Arguments.of("2024-01-32"),
                Arguments.of("2024-13-01"),
                Arguments.of("10000-13-01"));
    }

    @ParameterizedTest()
    @MethodSource("getValidDateTime")
    void Given_ValidDateTime_When_CallUserInputToLocalDateTime_ShouldReturnCorrectLocalDateTime(LocalDateTime localDateTime, String inputDateTime) throws InputException {
        assertEquals(localDateTime, DateTimeUtil.userInputToLocalDateTime(inputDateTime));
    }

    @ParameterizedTest()
    @MethodSource("getInvalidDateTime")
    void testUserInputToLocalDateTimeInvalidInput(String localDateTime) {
        assertThrows(InputException.class, () -> DateTimeUtil.userInputToLocalDateTime(localDateTime));
    }

    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 13, 14, 30);
        String cliOutput = "Nov 13 2024 14:30";
        assertEquals(cliOutput, DateTimeUtil.toString(dateTime));
    }

    @Test
    void testIsoToLocalDateTime() {
        String isoString = "2024-11-13T14:30:00";
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 13, 14, 30);
        assertEquals(localDateTime, DateTimeUtil.isoToLocalDateTime(isoString));
    }

    @Test
    void testToCsvString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 13, 14, 30);
        String isoDateTime = "2024-11-13T14:30:00";
        assertEquals(isoDateTime, DateTimeUtil.toIsoString(dateTime));
    }
}

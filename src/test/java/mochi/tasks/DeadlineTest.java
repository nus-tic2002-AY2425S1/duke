package mochi.tasks;

import mochi.storage.SaveManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    void setUp() throws IOException {
        deadline = new Deadline("Submit report", "15/11/2024 1500");
        SaveManager saveManager = new SaveManager();
        saveManager.deleteData();
    }

    @Test
    void testGetBy() {
        String expected = "Nov 15 2024, 3:00PM";
        assertEquals(expected, deadline.getBy(), "getBy should return the correct formatted deadline");
    }

    @Test
    void testCompareBefore() {
        assertTrue(deadline.compare("/before", "16/11/2024 1500"), "Deadline should be before this date");
        assertFalse(deadline.compare("/before", "14/11/2024 1500"), "Deadline should not be before this date");
    }

    @Test
    void testCompareAfter() {
        assertTrue(deadline.compare("/after", "14/11/2024 1400"), "Deadline should be after this date");
        assertFalse(deadline.compare("/after", "15/11/2024 1600"), "Deadline should not be after this date");
    }

    @Test
    void testToString() {
        String expected = "[D][ ] Submit report (by Nov 15 2024, 3:00PM)";
        assertEquals(expected, deadline.toString(), "toString should return the correct formatted string");
    }

    @Test
    void testToDBString() {
        String expected = "D||false||Submit report||15/11/2024 1500";
        assertEquals(expected, deadline.toDBString(), "toDBString should return the correct database-compatible format");
    }
}
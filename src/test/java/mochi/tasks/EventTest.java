package mochi.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mochi.storage.SaveManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
  private Event event;

  @BeforeEach
  void setUp() throws IOException {
    event = new Event("Team meeting", "15/11/2024 1000", "15/11/2024 1200");
    SaveManager saveManager = new SaveManager();
    saveManager.deleteData();
  }

  @Test
  void testGetFrom() {
    String expected = "Nov 15 2024, 10:00AM";
    assertEquals(expected, event.getFrom(), "get /From should return the correct start date in formatted string");
  }

  @Test
  void testGetTo() {
    String expected = "Nov 15 2024, 10:00AM";
    assertEquals(expected, event.getTo(), "get /To should return the correct end date in formatted string");
  }

  @Test
  void testCompareBefore() {
    assertTrue(event.compare("/before", "15/11/2024 1300"), "Event should end before this date");
    assertFalse(event.compare("/before", "15/11/2024 1100"), "Event should not end before this date");
  }

  @Test
  void testCompareAfter() {
    assertTrue(event.compare("/after", "15/11/2024 0900"), "Event should start after this date");
    assertFalse(event.compare("/after", "15/11/2024 1000"), "Event should not start after this date");
  }

  @Test
  void testToString() {
    String expected = "[E][ ] Team meeting (from: Nov 15 2024, 10:00AM to: Nov 15 2024, 12:00PM)";
    assertEquals(expected, event.toString(), "toString should return the correct formatted string for the event");
  }

  @Test
  void testToDBString() {
    String expected = "E||false||Team meeting||15/11/2024 1000||15/11/2024 1200";
    assertEquals(expected, event.toDBString(), "toDBString should return the correct database-compatible format");
  }
}

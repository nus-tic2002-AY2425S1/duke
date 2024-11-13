package mochi.tasks;
/**
 * Enum representing different types of tasks in the application.
 * <ul>
 *   <li>T: Represents a Todo task.</li>
 *   <li>D: Represents a Deadline task.</li>
 *   <li>E: Represents an Event task.</li>
 *   <li>UNKNOWN: Represents an unrecognized task type.</li>
 * </ul>
 */
public enum TaskType {
  T, D, E, UNKNOWN;
  public static TaskType getValue(String c) {
    try {
      return TaskType.valueOf(c.toUpperCase());
    } catch (IllegalArgumentException e) {
      return UNKNOWN;
    }
  }
}
package mochi.tasks;

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
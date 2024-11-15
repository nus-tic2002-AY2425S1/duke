package mochi.commands;

public enum CommandEnum {
  DELETE, MARK, UNMARK, VIEW, BYE, LIST, DEADLINE, EVENT, TODO, UNKNOWN;
  public static CommandEnum getValue(String c) {
    try {
      return CommandEnum.valueOf(c.toUpperCase());
    } catch (IllegalArgumentException e) {
      return UNKNOWN;
    }
  }
}
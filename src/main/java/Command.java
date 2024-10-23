enum Command {
  DELETE, MARK, UNMARK, BYE, LIST, DEADLINE, EVENT, TODO, UNKNOWN;
  public static Command getValue(String c) {
    try {
      return Command.valueOf(c.toUpperCase());
    } catch (IllegalArgumentException e) {
      return UNKNOWN;
    }
  }
}
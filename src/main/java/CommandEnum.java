enum CommandEnum {
  DELETE, MARK, UNMARK, BYE, LIST, DEADLINE, EVENT, TODO, UNKNOWN;
  public static CommandEnum getValue(String c) {
    try {
      return CommandEnum.valueOf(c.toUpperCase());
    } catch (IllegalArgumentException e) {
      return UNKNOWN;
    }
  }
}
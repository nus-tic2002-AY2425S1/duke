package duke.dancepop.enums;

public enum ActualCommandEnum {
    EVENT("event <description> /from <date/time> /to <to date/time>"),
    TODO("todo <description>"),
    DEADLINE("deadline <description> /by <date/time>"),
    LIST("list or list <date/time>"),
    BYE("bye"),
    MARK("mark <int value>"),
    UNMARK("unmark <int value>"),
    DELETE("delete <int value>");

    private final String value;

    ActualCommandEnum(String value) {
        this.value = value;
    }

    /**
     * @param command command enum to determine actual command usage
     * @return String describing command usage
     */
    public static String getActualCommand(CommandEnum command) {
        switch (command) {
            case TODO -> {
                return TODO.getValue();
            }
            case DEADLINE -> {
                return DEADLINE.getValue();
            }
            case EVENT -> {
                return EVENT.getValue();
            }
            case DELETE -> {
                return DELETE.getValue();
            }
            case UNMARK -> {
                return UNMARK.getValue();
            }
            case MARK -> {
                return MARK.getValue();
            }
            case LIST -> {
                return LIST.getValue();
            }
            case BYE -> {
                return BYE.getValue();
            }
        }
        return "";
    }

    public String getValue() {
        return value;
    }
}

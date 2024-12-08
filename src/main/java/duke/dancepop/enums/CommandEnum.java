package duke.dancepop.enums;

public enum CommandEnum {
    EVENT("event"),
    TODO("todo"),
    DEADLINE("deadline"),
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    SAVE("save"),
    LOAD("load");

    private final String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

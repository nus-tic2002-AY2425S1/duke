package duke.dancepop.enums;

public enum TaskEnum {
    EVENT("E"),
    TODO("T"),
    DEADLINE("D");

    private final String value;

    TaskEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

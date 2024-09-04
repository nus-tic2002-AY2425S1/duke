package duke.dancepop.enums;

public enum RegexEnum {
    STRING("(.+)"),
    DEADLINE("^(?<description>.*)\\s*/by\\s*(?<deadline>.*)$"),
    EVENT("^(?<description>.*)\\s*/from\\s*(?<start>.*)\\s*/to\\s*(?<end>.*)$");

    private final String value;

    RegexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String code;

    TaskType(String code) {
        this.code = code;
    }

    public static TaskType fromCode(String code) {
        for (TaskType type : TaskType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown task type: " + code);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

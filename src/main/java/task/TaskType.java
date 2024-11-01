package task;
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    // https://stackoverflow.com/questions/604424/how-to-get-an-enum-value-from-a-string-value-in-java
    public static TaskType getTaskType(String code) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.type.equals(code)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + code + " found");
    }
}

package wkduke.task;

/**
 * Enum representing the types of tasks available in the application.
 * Each task type is associated with a specific code used for encoding and decoding.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String code;

    /**
     * Constructs a {@code TaskType} with the specified code.
     *
     * @param code The code representing the task type.
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Retrieves the {@code TaskType} corresponding to the given code.
     *
     * @param code The code representing the task type.
     * @return The {@code TaskType} associated with the given code.
     * @throws IllegalArgumentException If the code does not match any task type.
     */
    public static TaskType fromCode(String code) {
        for (TaskType type : TaskType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown task type: " + code);
    }

    /**
     * Returns a string representation of the task type in lowercase.
     *
     * @return The name of the task type in lowercase as a {@code String}.
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

package wkduke.task;

/**
 * Enum representing the priority levels of a task.
 * Each priority level is associated with a specific code used for encoding and decoding.
 */
public enum TaskPriority {
    LOW("L"),
    MEDIUM("M"),
    HIGH("H");

    private final String code;

    /**
     * Constructs a {@code TaskPriority} with the specified code.
     *
     * @param code The code representing the priority level.
     */
    TaskPriority(String code) {
        this.code = code;
    }

    /**
     * Retrieves the {@code TaskPriority} corresponding to the given code.
     *
     * @param code The code representing the priority level.
     * @return The {@code TaskPriority} associated with the given code.
     * @throws IllegalArgumentException If the code does not match any priority level.
     */
    public static TaskPriority fromCode(String code) {
        for (TaskPriority priority : TaskPriority.values()) {
            if (priority.code.equals(code)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Unknown task priority level: " + code);
    }

    /**
     * Retrieves the code associated with the priority level.
     *
     * @return The code as a {@code String}.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns a string representation of the priority level in lowercase.
     *
     * @return The name of the priority level in lowercase as a {@code String}.
     */
    @Override
    public String toString() {
        return code;
    }
}
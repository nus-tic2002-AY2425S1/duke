package task;

import common.Constants;

/**
 * Represents the different types of tasks that can be created.
 * Each task has a corresponding code, 
 * i.e. TODO is represented by "T", DEADLINE is represented by "D", and EVENT is represented by "E"
 */
public enum TaskType {
    TODO(Constants.T),
    DEADLINE(Constants.D),
    EVENT(Constants.E),
    FIXED_DURATION(Constants.FD);

    private final String type;

    /**
     * Constructor for the {@code TaskType} enum. 
     * Constructs a {@code TaskType} with the specified code.
     * 
     * @param type represents the string code that represents the {@code TaskType}
     */
    TaskType(String type) {
        this.type = type;
    }

    /**
     * Returns the string code representing the {@code TaskType}.
     * 
     * @return the string code associated with the {@code TaskType}
     */
    @Override
    public String toString() {
        return type;
    }

    /**
     * Retrieves the corresponding {@code TaskType} based on the given string code.
     * 
     * @param code represents the string code that corresponds to the {@code TaskType}, i.e. "T", "D", or "E"
     * @return the {@code TaskType} that is associated with the given code
     * @throws IllegalArgumentException if the given code does not match an existing {@code TaskType}
     */
    // Solution below adapted from https://stackoverflow.com/questions/604424/how-to-get-an-enum-value-from-a-string-value-in-java
    public static TaskType getTaskType(String code) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.type.equals(code)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + code + " found");
    }
}

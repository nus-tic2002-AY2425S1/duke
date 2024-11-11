package task;

import common.Constants;

/**
 * Represents the different types of tasks that can be created.
 * Each task has a corresponding code,
 * i.e. TODO is represented by "T", DEADLINE is represented by "D",
 * EVENT is represented by "E" and FIXED_DURATION is represented by "FD"
 */
public enum TaskType {
    TODO(Constants.T),
    DEADLINE(Constants.D),
    EVENT(Constants.E),
    FIXED_DURATION(Constants.FD);

    private final String code;

    /**
     * Constructs the {@code TaskType} enum.
     * Constructs a {@code TaskType} with the specified code.
     *
     * @param code represents the string code that represents the {@code TaskType}.
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the string code representing the {@code TaskType}.
     */
    @Override
    public String toString() {
        return code;
    }

    /**
     * Retrieves the corresponding {@code TaskType} based on the given string code.
     *
     * @param code represents the string code that corresponds to the {@code TaskType}, i.e. "T", "D", or "E".
     * @return the {@code TaskType} that is associated with the given code.
     * @throws IllegalArgumentException if the given code does not match an existing {@code TaskType}.
     */
    // Solution below adapted from https://stackoverflow.com/questions/604424/how-to-get-an-enum-value-from-a-string-value-in-java
    public static TaskType getTaskType(String code) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.code.equals(code)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + code + " found");
    }

    /**
     * Retrieves the valid types of task.
     *
     * @return a String representation of the list of the types of tasks.
     */
    public static String getValidTaskType() {
        StringBuilder validTaskTypes = new StringBuilder();
        for (TaskType taskType : TaskType.values()) {
            // System.out.println(taskType.code);
            validTaskTypes.append("`").append(taskType.code).append("`").append(",");
        }
        if (!validTaskTypes.isEmpty()) {
            validTaskTypes.setLength(validTaskTypes.length() - 1);
        }
        return validTaskTypes.toString();
    }
}

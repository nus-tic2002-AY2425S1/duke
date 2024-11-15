package wkduke.command.update;


/**
 * Enum representing the fields by which tasks can be sorted.
 */
public enum SortField {
    PRIORITY("priority"),
    TASKTYPE("tasktype"),
    DATETIME("datetime");

    private final String fieldName;

    /**
     * Constructs a {@code SortField} with the specified field name.
     *
     * @param fieldName The name representing the sort field.
     */
    SortField(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Retrieves the {@code SortField} corresponding to the given field name.
     *
     * @param fieldName The name representing the sort field.
     * @return The {@code SortField} associated with the given field name.
     * @throws IllegalArgumentException If the field name does not match any sort field.
     */
    public static SortField fromFieldName(String fieldName) {
        for (SortField field : SortField.values()) {
            if (field.fieldName.equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Unknown sort field: " + fieldName);
    }

    /**
     * Returns the string representation of the sort field.
     *
     * @return The field name as a {@code String}.
     */
    @Override
    public String toString() {
        return fieldName;
    }
}


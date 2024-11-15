package wkduke.command.update;


/**
 * Enum representing the order in which tasks can be sorted.
 */
public enum SortOrder {
    ASCENDING("asc"),
    DESCENDING("desc");

    private final String code;

    /**
     * Constructs a {@code SortOrder} with the specified code.
     *
     * @param code The code representing the sort order.
     */
    SortOrder(String code) {
        this.code = code;
    }

    /**
     * Retrieves the {@code SortOrder} corresponding to the given code.
     *
     * @param code The code representing the sort order.
     * @return The {@code SortOrder} associated with the given code.
     * @throws IllegalArgumentException If the code does not match any sort order.
     */
    public static SortOrder fromCode(String code) {
        for (SortOrder order : SortOrder.values()) {
            if (order.code.equalsIgnoreCase(code)) {
                return order;
            }
        }
        throw new IllegalArgumentException("Unknown sort order: " + code);
    }

    /**
     * Returns the string representation of the sort order.
     *
     * @return The sort order code as a {@code String}.
     */
    @Override
    public String toString() {
        return code;
    }
}

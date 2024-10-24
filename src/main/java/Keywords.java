public enum Keywords {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String keyword;

    Keywords(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword() {
        return keyword;
    }
    public static boolean isValidKeyword(String keyword) {
        // Iterating each Enum value and check against the input keyword
        for (Keywords keywords : Keywords.values()) {
            if (keywords.getKeyword().equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
}

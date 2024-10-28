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
}

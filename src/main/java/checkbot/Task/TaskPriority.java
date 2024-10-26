package checkbot.Task;

public enum TaskPriority {
    HIGH, MEDIUM, LOW, NOT_SET;

    public String getPriority() {
        return switch (this) {
            case HIGH -> "HIGH";
            case MEDIUM -> "MEDIUM";
            case LOW -> "LOW";
            case NOT_SET -> "NOT SET";
        };
    }
}

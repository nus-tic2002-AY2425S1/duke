package checkbot.task;

public enum TaskPriority {
    HIGH, MEDIUM, LOW, NOT_SET;

    public String getPriorityIcon() {
        return switch (this) {
            case HIGH -> "!!!";
            case MEDIUM -> " !!";
            case LOW -> "  !";
            case NOT_SET -> "   ";
        };
    }

    public String toString() {
        return switch (this) {
            case HIGH -> "HIGH";
            case MEDIUM -> "MEDIUM";
            case LOW -> "LOW";
            case NOT_SET -> "NOT SET";
        };
    }
}

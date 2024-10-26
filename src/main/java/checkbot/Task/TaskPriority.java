package checkbot.Task;

public enum TaskPriority {
    HIGH, MEDIUM, LOW, NOT_SET;

    public String getPriority() {
        return switch (this) {
            case HIGH -> "High";
            case MEDIUM -> "Medium";
            case LOW -> "Low";
            case NOT_SET -> "Not Set";
        };
    }
}

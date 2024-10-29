package checkbot.task;

public enum TaskType {
    TODO, DEADLINE, EVENT;

    public String getTaskIcon(){
        return switch (this) {
            case TODO -> "T";
            case DEADLINE -> "D";
            case EVENT -> "E";
        };
    }
}

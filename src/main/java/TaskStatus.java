public enum TaskStatus {
    DONE, NOT_DONE;

    public String getStatusIcon() {
        return switch (this) {
            case DONE -> "X";
            case NOT_DONE -> " ";
        };
    }

    public int getBoolean() {
        if (this == DONE) {
            return 1;
        } else {
            return 0;
        }
    }
}

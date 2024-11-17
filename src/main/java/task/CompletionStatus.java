package task;

import common.Constants;

public enum CompletionStatus {

    DONE("1"),
    NOT_DONE("0");

    private final String status;

    CompletionStatus(String status) {
        this.status = status;
    }

    private String getStatus() {
        return status;
    }

    public static CompletionStatus getStatus(String status) {
        for (CompletionStatus completionStatus : CompletionStatus.values()) {
            if (completionStatus.getStatus().equals(status)) {
                return completionStatus;
            }
        }
        throw new IllegalArgumentException();
    }

}

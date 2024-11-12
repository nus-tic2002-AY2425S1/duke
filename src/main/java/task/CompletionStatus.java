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

    public static String getValidStatus() {
        StringBuilder completionStatus = new StringBuilder();
        for (CompletionStatus status : CompletionStatus.values()) {
            completionStatus.append(Constants.BACKTICK).append(status.status)
                .append(Constants.BACKTICK).append(Constants.COMMA);
        }
        if (!completionStatus.isEmpty()) {
            completionStatus.setLength(completionStatus.length() - 1);
        }
        return completionStatus.toString();
    }

}

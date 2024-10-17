package checkbot.Task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        taskType = TaskType.DEADLINE;
        this.dueDateTime = dueDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime.getDayOfMonth() + "/" + dueDateTime.getMonthValue() + "/" + dueDateTime.getYear() +
                " " + String.format("%02d", dueDateTime.getHour()) + String.format("%02d", dueDateTime.getMinute());
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (by: " + this.getDueDateTime() + ")";
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.getDescription() + " /by " +
                this.getDueDateTime();
    }
}

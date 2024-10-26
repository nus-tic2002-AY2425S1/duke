package checkbot.Task;

import checkbot.Ui.TextUi;

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

    // TODO: add priority in list view for storage
    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (by: " + TextUi.printDateTime(dueDateTime) + ")";
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.getDescription() + " /by " +
                this.getDueDateTime();
    }
}

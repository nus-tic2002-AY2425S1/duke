package checkbot.task;

import checkbot.ui.TextUi;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        taskType = TaskType.DEADLINE;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Returns due datetime in DD/MM/YYYY HHMM(24H) format.
     */
    public String getDueDateTime() {
        return dueDateTime.getDayOfMonth() + "/" + dueDateTime.getMonthValue() + "/" + dueDateTime.getYear() +
                " " + String.format("%02d", dueDateTime.getHour()) + String.format("%02d", dueDateTime.getMinute());
    }

    /**
     * Displays task in list view for user to see in UI.
     */
    public String getListView() {
        return "[" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (by: " + TextUi.printDateTime(dueDateTime) + ")";
    }

    /**
     * Stringifies task for storing of task in storage file.
     */
    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.priority.toString() + " | " +
                this.getDescription() + " /by " +
                this.getDueDateTime();
    }
}

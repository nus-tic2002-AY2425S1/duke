package checkbot.task;

import checkbot.Ui.TextUi;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        taskType = TaskType.DEADLINE;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Gets due datetime in DD/MM/YYYY HHMM(24H) format.
     *
     * @return String
     */
    public String getDueDateTime() {
        return dueDateTime.getDayOfMonth() + "/" + dueDateTime.getMonthValue() + "/" + dueDateTime.getYear() +
                " " + String.format("%02d", dueDateTime.getHour()) + String.format("%02d", dueDateTime.getMinute());
    }

    /**
     * Display task in list view for user to see in UI.
     *
     * @return String
     */
    public String getListView() {
        return "[" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (by: " + TextUi.printDateTime(dueDateTime) + ")";
    }

    /**
     * Stringify task for storing of task in storage file.
     *
     * @return String
     */
    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.priority.toString() + " | " +
                this.getDescription() + " /by " +
                this.getDueDateTime();
    }
}

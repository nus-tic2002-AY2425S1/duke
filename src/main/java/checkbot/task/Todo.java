package checkbot.task;

import checkbot.ui.TextUi;

import java.time.LocalDateTime;

public class Todo extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    // normal todo task
    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
        this.startDateTime = null;
        this.endDateTime = null;
    }

    // period todo task
    public Todo(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.taskType = TaskType.TODO;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns start datetime in DD/MM/YYYY HHMM(24H) format.
     */
    public String getStartDateTime() {
        return startDateTime.getDayOfMonth() + "/" + startDateTime.getMonthValue() + "/" + startDateTime.getYear() +
                " " + String.format("%02d", startDateTime.getHour()) + String.format("%02d", startDateTime.getMinute());
    }

    /**
     * Returns end datetime in DD/MM/YYYY HHMM(24H) format.
     */
    public String getEndDateTime() {
        return endDateTime.getDayOfMonth() + "/" + endDateTime.getMonthValue() + "/" + endDateTime.getYear() +
                " " + String.format("%02d", endDateTime.getHour()) + String.format("%02d", endDateTime.getMinute());
    }

    /**
     * Displays task in list view for user to see in UI.
     */
    public String getListView() {
        if (startDateTime == null && endDateTime == null) {
            // normal todo task
            return TaskList.tasks.indexOf(this)+1 + ". [" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" +
                    this.getStatusIcon() + "] " + description;
        } else {
            // period todo task
            return TaskList.tasks.indexOf(this)+1 + ". [" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" +
                    this.getStatusIcon() + "] " + description + " (between: " + TextUi.printDateTime(startDateTime) +
                    " and: " + TextUi.printDateTime(endDateTime) + ")";
        }
    }

    /**
     * Stringifies task for storing of task in storage file.
     */
    public String getFileView() {
        if (startDateTime == null && endDateTime == null) {
            // normal todo task
            return this.getTaskIcon() + " | " +
                    this.status.toString() + " | " +
                    this.priority.toString() + " | " +
                    this.getDescription();
        } else {
            // period todo task
            return this.getTaskIcon() + " | " +
                    this.status.toString() + " | " +
                    this.priority.toString() + " | " +
                    this.getDescription() + " /between " +
                    this.getStartDateTime() + " /and " +
                    this.getEndDateTime();
        }
    }
}

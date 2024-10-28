package checkbot.task;

import checkbot.Ui.TextUi;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        taskType = TaskType.EVENT;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets start datetime in DD/MM/YYYY HHMM(24H) format.
     *
     * @return String
     */
    public String getStartDateTime() {
        return startDateTime.getDayOfMonth() + "/" + startDateTime.getMonthValue() + "/" + startDateTime.getYear() +
                " " + String.format("%02d", startDateTime.getHour()) + String.format("%02d", startDateTime.getMinute());
    }

    /**
     * Gets end datetime in DD/MM/YYYY HHMM(24H) format.
     *
     * @return String
     */
    public String getEndDateTime() {
        return endDateTime.getDayOfMonth() + "/" + endDateTime.getMonthValue() + "/" + endDateTime.getYear() +
                " " + String.format("%02d", endDateTime.getHour()) + String.format("%02d", endDateTime.getMinute());
    }

    /**
     * Display task in list view for user to see in UI.
     *
     * @return String
     */
    public String getListView() {
        return "[" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (from: " + TextUi.printDateTime(startDateTime) + " to: " + TextUi.printDateTime(endDateTime) + ")";
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
                this.getDescription() + " /from " +
                this.getStartDateTime() + " /to " +
                this.getEndDateTime();
    }
}

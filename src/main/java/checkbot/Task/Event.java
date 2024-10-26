package checkbot.Task;

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

    public String getStartDateTime() {
        return startDateTime.getDayOfMonth() + "/" + startDateTime.getMonthValue() + "/" + startDateTime.getYear() +
                " " + String.format("%02d", startDateTime.getHour()) + String.format("%02d", startDateTime.getMinute());
    }

    public String getEndDateTime() {
        return endDateTime.getDayOfMonth() + "/" + endDateTime.getMonthValue() + "/" + endDateTime.getYear() +
                " " + String.format("%02d", endDateTime.getHour()) + String.format("%02d", endDateTime.getMinute());
    }

    public String getListView() {
        return "[Priority: " + this.getPriority() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (from: " + TextUi.printDateTime(startDateTime) + " to: " + TextUi.printDateTime(endDateTime) + ")";
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.priority.getPriority() + " | " +
                this.getDescription() + " /from " +
                this.getStartDateTime() + " /to " +
                this.getEndDateTime();
    }
}

package KLBot.TaskList;

public class Task {
    protected boolean completed;
    protected String listDescription;

    public Task(String listDescription) {
        this.listDescription = listDescription;
        this.completed = false;
    }

    public void markAsCompleted() {
        completed = true;
    }

    public void markAsIncomplete() {
        completed = false;
    }

    public String getDescription() {
        return listDescription;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " ");
    }

    public String getType() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + listDescription;
    }

    public String toFileFormat() {
        return " | " + (completed ? "1" : "0") + " | " + listDescription;
    }

    public static Task fromFileFormat(String taskData) {
        String[] parts = taskData.split(" \\| ");

        if (parts.length >= 3) {
            if (parts[0].equals("T")) {
                ToDo task = new ToDo(parts[2].trim());
                if (parts[1].equals("1")) {
                    task.markAsCompleted();
                }
                return task;
            } else if (parts[0].equals("D")) {
                if (parts.length == 4) {
                    Deadline task = new Deadline(parts[2].trim(), parts[3].trim());
                    if (parts[1].equals("1")) {
                        task.markAsCompleted();
                    }
                    return task;
                }
            } else if (parts[0].equals("E")) {
                if (parts.length == 5) {
                    Event task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                    if (parts[1].equals("1")) {
                        task.markAsCompleted();
                    }
                    return task;
                }
            }
        }

        return null;
    }
}

package ruan.task;

import ruan.exception.ErrorType;
import ruan.exception.RuanException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        //set all item to not done by default
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return task status
        return (isDone ? "X" : " ");
    }

    //set task status
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String toFileFormat();

    public static Task fromFileFormat(String fileLine) throws RuanException {
        String[] content = fileLine.split(" \\| ");
        if (content.length < 3) {
            throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
        }
    
        String taskType = content[0];
        boolean isDone = content[1].equals("1");
        String description = content[2].trim();
        Task task;
        
        //check and read task from file for different command
        switch (taskType) {
            case "T": 
                task = new Todo(description);
            break;
            case "D":
                String[] deadlineData = description.split(" /by ");
                if (deadlineData.length != 2) {
                    throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
                }
                String deadlineDescription = deadlineData[0].trim();
                String by = deadlineData[1].trim();
                task = new Deadline(deadlineDescription, by);
            break;
            case "E":
                String[] eventData = description.split(" /from | /to ");
                if (eventData.length != 3) {
                    throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
                }
                String eventDescription = eventData[0].trim();
                String from = eventData[1].trim();
                String to = eventData[2].trim();
                task = new Event(eventDescription, from, to);
            break;
            default:
                throw new RuanException(ErrorType.INVALID_TASK_TYPE);
        }
    
        task.setDone(isDone);
        return task;
    }


    @Override
    public String toString() {
        //return formatted string
        return "[" + getStatusIcon() + "] " + description; 
    }
}

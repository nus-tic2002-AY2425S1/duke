package josbot.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsNotDone(){
        isDone = false;
    }

    public String getType(){ return "";};

    public LocalDateTime getDateTime(){ return null; };

    public String toString(){
        return "["+getStatusIcon()+"] "+description;
    }
}



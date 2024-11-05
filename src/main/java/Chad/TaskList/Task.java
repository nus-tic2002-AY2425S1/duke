package Chad.TaskList;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime completeTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription()
    {
        return this.description;
    }
    public void setTask()
    {
        this.isDone=true;
        this.completeTime=LocalDateTime.now();
    }
    public void setCompleteTime(LocalDateTime completeTime)
    {
        this.completeTime=completeTime;
    }
    public LocalDateTime getCompleteTime(){
        return this.completeTime;
    }

    public void unSetTask()
    {
        this.isDone=false;
        this.completeTime=null;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    //...

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") +" " + this.description;
    }
}
package josbot.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void setTag(String tag) { this.tag = tag; }

    public String getTag() {
        if(tag.equals(""))
        {
            return "";
        }
        else
        {
            return tag;
        }
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
        if(getTag().isEmpty())
        {
            return "["+getStatusIcon()+"] "+description;
        }
        else
        {
            return "["+getStatusIcon()+"] [#"+getTag()+"] "+description;
        }

    }
}



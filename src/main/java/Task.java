public class Task {
    protected String description;
    protected boolean isDone;

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
    }

    public void unSetTask()
    {
        this.isDone=false;
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
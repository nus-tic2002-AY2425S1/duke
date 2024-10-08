public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString(){
        return "description: " + description;
    }

    public String toSave(){
        return (isDone ? " 1 " : " 0 " ) + description;
    }

}
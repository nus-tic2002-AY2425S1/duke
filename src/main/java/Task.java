public class Task {
    protected boolean isDone;
    protected String description;

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        isDone = true;
    }

    public void unmarkDone(){
        isDone = false;
    }

    @Override
    public String toString(){
        return "[" + (isDone? "X" : " ") + "] " + description;
    }

}

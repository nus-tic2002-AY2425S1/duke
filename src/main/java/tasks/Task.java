package tasks;

import exception.DukeException;

public abstract class Task {
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

    public abstract Task createTask(String description) throws DukeException;
    public abstract String toFileFormat() throws DukeException;
}

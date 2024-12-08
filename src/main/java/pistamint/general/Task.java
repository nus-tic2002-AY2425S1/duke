package pistamint.general;

import pistamint.*;

public class Task implements Cloneable {
    protected String description;
    protected boolean isDone;
    protected char symbol;

    public Task(String description, char symbol) {
        this.description = description;
        this.symbol = symbol;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUnDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
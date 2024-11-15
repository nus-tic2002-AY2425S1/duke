package jibberJabber.tasks.taskType;

import jibberJabber.tasks.Task;

public class Event extends Task {
    public String from;
    public String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }
    @Override
    public String printAddedTask(){
        return "\t[E]" + super.printAddedTask()  + " (from: " + from + " to: " + to + ")";
    }
}

package jibberJabber.tasks.taskType;

import jibberJabber.tasks.Task;

public class Deadline extends Task {
    public String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
    @Override
    public String printAddedTask(){
        return "\t[D]" + super.printAddedTask()  + " (by: " + by + ")";
    }
}
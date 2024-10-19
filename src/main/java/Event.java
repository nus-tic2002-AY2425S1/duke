public class Event extends Task{
    protected String from;
    protected String to;

    // Construct an event task with schedule timing
    public Event(String taskName, String from, String to){
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    protected  String getTaskType(){
        return "E";
    }

    // output the event to string with [E] and the schedule timeline for event from till to.
    @Override
    public String toString(){
        return "[" + getTaskType() + "][" + getStatusIcon() +"] " + taskName + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName + " | " + from + " | " + to;
    }

}

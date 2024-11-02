package starkchatbot.taskmanager;

public class Event extends Task{


    public Event(String task, String startAt, String endBy) {
        super(task+ " (from: " + startAt + " to: " + endBy + ")");


    }

    @Override
    public String printTask() {
        return "[E] " + super.printTask();
    }
}

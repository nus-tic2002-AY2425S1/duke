public class Event extends Task{
    private String startAt;
    private String endBy;

    public Event(String task, String time){
        super(task);
        String[] timeSplit = time.split(" /to ");
        startAt = timeSplit[0];
        endBy = timeSplit[1];
    }

    @Override
    public String printTask() {
        return "[E] " + super.printTask() + " (from: " + startAt + " to: " + endBy + " )";
    }
}

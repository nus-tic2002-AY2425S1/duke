package tasks;

public class Event extends Task{
    public String from;
    public String to;

    public Event (String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public Task createTask(String eventString){
        String [] eventParts = eventString.split(" /from | /to ");
        return new Event(eventParts[0], eventParts[1], eventParts[2]);
    }
    
}

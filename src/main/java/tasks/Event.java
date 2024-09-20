package tasks;

import exception.DukeException;

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
    public Task createTask(String eventString) throws DukeException {
        String [] eventParts = eventString.split(" /from | /to ");
        if(eventParts.length != 3){
            throw new DukeException("OOPS!! The Event description is improperly formatted. Please try again!");
        }
        return new Event(eventParts[0], eventParts[1], eventParts[2]);
    }
    
}

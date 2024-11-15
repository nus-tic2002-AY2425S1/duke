package josbot.task;

import josbot.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to  = to;
    }

    public LocalDateTime getTo(){
        return to;
    }
    public LocalDateTime getFrom(){
        return from;
    }

    @Override
    public LocalDateTime getDateTime(){
        return from;
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String toString(){
        DateTimeParser dt_parser = new DateTimeParser();
        return "[E]"+super.toString()+" (From: "+dt_parser.convertToString(getFrom(),"view")+" To: "+dt_parser.convertToString(getTo(),"view")+")";
    }
    //have to change this
}

package General;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private String from;
    private String to;
    public Event(String description, char symbol,String from, String to){
        super(description, symbol);
        this.from ="(from:"+from.toString();
        this.to = "to:"+to+")";
        this.isDone=false;
    }
    public Event(String description, char symbol){
        super(description, symbol);
        from=description.substring(description.indexOf("(from:")+7,description.indexOf("to"));
        to=description.substring(description.indexOf("to:")+4,description.indexOf(")"));
        this.from="(from:"+from;
        this.to="to:"+to+")";
        this.isDone=false;
        setDescription(description.substring(0,description.indexOf("(")));
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    @Override
    public String getDescription(){
        return super.getDescription()+from+to;
    }
}

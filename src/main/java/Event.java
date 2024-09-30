public class Event extends Task{
    private String from;
    private String to;
    public Event(String description, char symbol,String from, String to){
        super(description, symbol);
        this.from = "(from:"+from;
        this.to = "to:"+to+")";
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

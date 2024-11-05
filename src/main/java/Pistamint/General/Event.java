package Pistamint.General;

public class Event extends Task{
    private String from;
    private String to;
    public Event(String description, char symbol,String from, String to){
        super(description, symbol);
        this.from =" (from:"+from;
        this.to = " to:"+to+")";
        this.isDone=false;
    }
    public Event(String description, char symbol){
        super(description, symbol);
        String from,to;
        //System.out.println(description.lastIndexOf("to"));
        from=description.substring(description.indexOf("(from:")+6,description.lastIndexOf("to"));
        to=description.substring(description.indexOf("to:")+3,description.lastIndexOf(")"));
        this.from="(from:"+from;
        this.to="to:"+to+")";
        this.isDone=false;
        setDescription(description.substring(0,description.lastIndexOf("(")));
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public void setFrom(String from) {
        this.from = "(from:"+from+" ";
    }
    public void setTo(String to) {
        this.to = "to:"+to+")";
    }
    @Override
    public String getDescription(){
        return super.getDescription()+getFrom()+getTo();
    }
    public String getOnlyDescription(){
        return super.getDescription();
    }
}

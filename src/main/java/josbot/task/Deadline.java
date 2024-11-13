package josbot.task;

import josbot.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    protected boolean time;
    //inspired by https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/
    static final DateTimeFormatter FORMATTER_DISPLAY_DATETIME= DateTimeFormatter.ofPattern("dd MMMM yyyy 'at' hh:mm a");
    static final DateTimeFormatter FORMATTER_DISPLAY_DATE= DateTimeFormatter.ofPattern("dd MMMM yyyy");
    static final DateTimeFormatter FORMATTER_STORE_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy,HHmm");
    static final DateTimeFormatter FORMATTER_STORE_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");


//    public Deadline(String description, String by){
//        super(description);
//        this.by = by;
//    }

    public Deadline(String description, LocalDateTime by, boolean time) {
        super(description);
        //DateTimeParser parser = new DateTimeParser();
        this.by =  by;
        this.time = time;
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        //DateTimeParser parser = new DateTimeParser();
        this.by =  by;
        this.time = false;
    }

    public String getBy(){
        if(time)
        {
            return by.format(FORMATTER_DISPLAY_DATETIME);
        }
        else {
            return by.format(FORMATTER_DISPLAY_DATE);
        }
    }

    public String getByToStore(){
        if(time)
        {
            return by.format(FORMATTER_STORE_DATETIME);
        }
        else {
            return by.format(FORMATTER_STORE_DATE);
        }
    }

    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String toString(){
        return "[D]"+super.toString()+" (by: "+getBy()+")";
    }
}

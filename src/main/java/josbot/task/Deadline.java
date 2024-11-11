package josbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    protected boolean time;
    static final DateTimeFormatter FORMATTERDATETIME= DateTimeFormatter.ofPattern("dd MMMM yyyy 'at' hh:mm a");
    static final DateTimeFormatter FORMATTERDATE= DateTimeFormatter.ofPattern("dd MMMM yyyy");


//    public Deadline(String description, String by){
//        super(description);
//        this.by = by;
//    }

    public Deadline(String description, String deadline_datetime){
        super(description);
        this.by = convertDateTime(deadline_datetime);
    }

    private LocalDateTime convertDateTime(String deadline_datetime){
        String[] datetime = deadline_datetime.split(" ");
        LocalDateTime dt = null;
        if(datetime.length == 2)
        {
            String[] date = datetime[0].split("/");
            String hour = datetime[1].substring(0, 2);
            String minute = datetime[1].substring(2, 4);
            dt = LocalDateTime.parse(date[2]+"-"+date[1]+"-"+date[0]+"T"+hour+":"+minute);
            time = true;
        }
        else if(datetime.length == 1)
        {

            String[] date = datetime[0].split("/");
            dt = LocalDateTime.parse(date[2]+"-"+date[1]+"-"+date[0]+"T00:00");
            time = false;

        }
        else
        {
            System.out.println("Invalid date & time format! Please use date format (dd-MM-yyyy) or if you want to add time (24-hour format, eg. 1800 is 6PM)");
        }
        return dt;
    }

    public String getBy(){
        if(time)
        {
            return by.format(FORMATTERDATETIME);
        }
        else {
            return by.format(FORMATTERDATE);
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

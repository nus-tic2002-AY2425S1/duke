package Chad.Parser;


import Chad.Ui.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ChadDate {

    public static String parseDate(String inputDate){


        try {
            LocalDate parsedDate = LocalDate.parse(inputDate);
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        } catch (DateTimeParseException e) {
            // should allow user put any thing, example "/by im not sure"
            //how to notify user without throw exception(break the code run)
            // can I just print a line here, but will vilate single responsibility principle
            Ui.showMsg("Opps!!date not recongized,however I'll record it. correct date format: YYYY-MM-DD");
            return inputDate;
        }

    }

    public static boolean isDate(String inputDate){

        try {
            LocalDate.parse(inputDate);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public static boolean sameDay(String date1,String date2)
    {
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        return d1.equals(d2);
    }

}
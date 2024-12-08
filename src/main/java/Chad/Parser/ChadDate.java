package Chad.Parser;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChadDate {

    public static String parseDate(String inputDate) {


        try {
            LocalDate parsedDate = LocalDate.parse(inputDate);
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        } catch (DateTimeParseException e) {
            // should allow user put any thing, example "/by im not sure"
            //how to notify user without throw exception(break the code run)
            // can I just print a line here, but will vilate single responsibility principle
            //ui.showMsg("Opps!!date not recongized,however I'll record it. correct date format: YYYY-MM-DD");
            return inputDate;
        }

    }

    public static boolean isDate(String inputDate) {

        try {
            LocalDate.parse(inputDate);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public static boolean sameDay(String date1, String date2) {
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        return d1.equals(d2);
    }

    /**
     * Converts a string representation of a time period into a Period object.
     * It understands formats like "1 week", "3 days", "last week", etc.
     *
     * @param timePeriodString The string representing the time period (e.g., "1 week", "last day").
     * @return The corresponding Period object.
     */
    public static Period findPeriodByString(String timePeriodString) {
        // Prepare to look for specific patterns in the string
        Pattern pattern = Pattern.compile("(\\d+)\\s*(days?|weeks?|months?)|last\\s*(day|week|month)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(timePeriodString);

        Period totalPeriod = Period.ZERO; // Start with a zero period

        while (matcher.find()) {
            // Check for numeric periods first
            if (matcher.group(1) != null) {
                int amount = Integer.parseInt(matcher.group(1)); // Get the amount (number) before the unit
                String unit = matcher.group(2).toLowerCase(); // Get the unit
                // Assert that the amount is greater than 0
                assert amount > 0 : "Amount must be greater than 0.";

                switch (unit) {
                case "day":
                //Fallthrough
                case "days":
                    totalPeriod = totalPeriod.plusDays(amount); // Add days to the total period
                    break;
                case "week": 
                //Fallthrough
                case "weeks":
                    totalPeriod = totalPeriod.plus(Period.ofWeeks(amount)); // Add weeks to the total period
                    break;
                case "month":
                //Fallthrough
                case "months":
                    totalPeriod = totalPeriod.plusMonths(amount); // Add months to the total period
                    break;
                }
            }

            // For phrases with "last"
            if (matcher.group(3) != null) { // Check matched group for "last day/week/month"
                String lastUnit = matcher.group(3).toLowerCase();
                switch (lastUnit) {
                case "day":
                    totalPeriod = totalPeriod.plusDays(1); // Add one day for last day
                    break;
                case "week":
                    totalPeriod = totalPeriod.plus(Period.ofWeeks(1)); // Add one week for last week
                    break;
                case "month":
                    totalPeriod = totalPeriod.plusMonths(1); // Add one month for last month
                    break;
                }
            }
        }

        return totalPeriod; // Return the constructed Period
    }

}
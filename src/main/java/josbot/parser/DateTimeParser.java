package josbot.parser;

import josbot.JosBotException;
import josbot.ui.UI;

import java.time.LocalDateTime;

public class DateTimeParser {

    /**
     *
     * Convert date/date & time in String format to LocalDateTime format
     *
     *
     * @param deadline_datetime
     * @return Converted date/date & time in LocalDateTime format
     */
    public LocalDateTime convertDateTime(String deadline_datetime) throws IndexOutOfBoundsException, JosBotException {
        String[] datetime = deadline_datetime.split(" ");
        LocalDateTime dt = null;
        UI ui = new UI();
            if (datetime.length == 2) {
                String[] date = datetime[0].split("/");
                String hour = datetime[1].substring(0, 2);
                String minute = datetime[1].substring(2, 4);
                dt = LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + "T" + hour + ":" + minute);
            } else if (datetime.length == 1) {

                String[] date = datetime[0].split("/");
                dt = LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + "T00:00");
            } else {
                throw new JosBotException("");
            }
        return dt;
    }
}

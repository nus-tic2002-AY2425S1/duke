package josbot.parser;

import josbot.task.Deadline;
import josbot.task.Event;
import josbot.task.Task;

import java.util.ArrayList;

/**
 * Used to parse Storage related function
 */

public class StorageParser {
    /**
     * Returns a String format that's suitable to be used to store the current
     * task that have been executed
     *
     * @param fileList An Array List that contains Task that user have executed
     * @return A suitable String format of the task to be stored
     */
    public static String parseListToString(ArrayList<Task> fileList) {
        String listString = "";
        for (int i = 0; i < fileList.size(); i++) {
            listString += fileList.get(i).getType() + ",";
            if (fileList.get(i).getStatusIcon().equals("X")) {
                listString += "1,";
            } else {
                listString += "0,";
            }
            listString += fileList.get(i).getDescription();

            //deadline
            if (fileList.get(i).getType().equals("D")) {
                Deadline d = (Deadline) fileList.get(i);
                DateTimeParser dtParser = new DateTimeParser();
                listString += "," + dtParser.convertToString(d.getDateTime(), "store");
            } else if (fileList.get(i).getType().equals("E")) {
                Event e = (Event) fileList.get(i);
                DateTimeParser dtParser = new DateTimeParser();
                listString += "," + dtParser.convertToString(e.getFrom(), "store") + "," + dtParser.convertToString(e.getTo(), "store");
            }

            if (!fileList.get(i).getTag().equals("")) {
                listString += "#" + fileList.get(i).getTag();
            }

            listString += "\n";
        }
        return listString;
    }
}

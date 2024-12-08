package parser;

import exception.DukeException;

/**
 * Handles Task parsing
 * Solution below adapted from https://stackoverflow.com/questions/4444341/split-a-string-into-a-string-of-a-specific-size
 */
public class TaskParser {

    public static String [] parseTask(String commandString) {
        String [] taskParts = commandString.split(" ", 2);
        taskParts[0] = taskParts[0].toLowerCase();
        if (taskParts.length == 2) {
            taskParts[1] = taskParts[1].trim();
        } else {
            taskParts = new String[]{taskParts[0], null};
        }
        return taskParts;
    }
    public static String [] parseEvent(String eventString) throws DukeException {
        String [] eventParts = eventString.split(" /from | /to ");
        if(eventParts.length != 3){
            throw new DukeException("OOPS!! The Event description is improperly formatted. Please try again!");
        }
        return eventParts;
    }

    public static String [] parseDeadline(String deadlineString) throws DukeException{
        String[] deadlineParts = deadlineString.split(" /by ");
        if (deadlineParts.length != 2) {
            throw new DukeException("OOPS!! The Deadline description is improperly formatted. Please try again!");
        }
        deadlineParts[0] = deadlineParts[0].trim();
        return deadlineParts;
    }
    public static String parseUpdate(String updateString) throws DukeException {
        String[] updateParts = updateString.split(" ", 4);
        if (updateParts.length < 4) {
            throw new DukeException("OOPS!! Please provide a update format!");
        }
        return updateParts[3];
    }

    public static String [] parseFixedDuration(String parseFixedDurationString) throws DukeException{
        String[] fixedDurationParts= parseFixedDurationString.split(" /duration ");
        if (fixedDurationParts.length != 2) {
            throw new DukeException("OOPS!! The FixedDuration description is improperly formatted. Please try again!");
        }
        fixedDurationParts[0] = fixedDurationParts[0].trim();
        return fixedDurationParts;
    }

}

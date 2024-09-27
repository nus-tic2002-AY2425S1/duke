// https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}



/*
 * Exception #1 (TaskException): Missing / empty description (for Todo, Deadline & Event) or date/time for Deadline & Event
 * Exception #2 (CommandException): Invalid command (does not start with todo / deadline / event)
 * Exception #3 (TaskException): User input non-number for mark / unmark tasks (NumberFormatException?)
 * Exception #4 (TaskException): User input out of range index for mark / unmark tasks (can be < 0 or > list.length())
 * 
 * 
 */
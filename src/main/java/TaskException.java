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
 * There can be different variations, e.g.
 * For deadline, 
 * deadline                     --> missing description and /by information
 * deadline <description>       --> missing /by information
 * deadline by                  --> in this case, "by" goes under description
 * deadline /by                 --> missing description
 * deadline /by <description>   --> no error, task is added to list
 * 
 * For event,
 * event                                                    --> missing description and /from and /to information
 * event <description>                                      --> missing /from and /to information
 * event <description> /to                                  --> missing from
 * event <description> /from                                --> missing to
 * event <description> /from <date/time> /to <date/time>    --> no error, task is added to list
 * 
 * event
 * event project meeting
 * event project meeting /from Mon 2pm
 * event project meeting /to 4pm
 * event /from Mon 2pm
 * event /to 4pm
 * event /from Mon 2pm /to 4pm
 * event project meeting /from Mon 2pm /to 4pm      --> only this task is added successfully
 * 
 * Future exceptions: End time is before start time. 
 * 
 * ...
 * 
 */
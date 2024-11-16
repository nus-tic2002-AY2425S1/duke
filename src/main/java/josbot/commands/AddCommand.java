package josbot.commands;

import josbot.JosBotException;
import josbot.parser.DateTimeParser;
import josbot.storage.FileStorage;
import josbot.task.Todo;
import josbot.task.Deadline;
import josbot.task.Event;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 *
 * This Command is initialised when user executes the 'todo', 'deadline' or 'event' command
 *
 */

public class AddCommand extends Command {

    private Task t;
    private DateTimeParser dt;

    public AddCommand(String commandType, String description) {
        super(commandType, description);
    }

    /**
     *
     * Execute Command for Todo, Deadline and Event.
     * It will create the necessary Tasks and add it into the TaskList
     *
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui A class to generate messages
     * @param file It stores the filepath and method related to storing and saving of the file
     * @throws JosBotException
     */

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException{
        try{

            if(commandType.equals("todo")){
                t = new Todo(description);
            }
            else if(commandType.equals("deadline")){
                dt = new DateTimeParser();
                String[] deadlineTxt = description.split("/by", 2);
                String[] datetime = deadlineTxt[1].trim().split(" ");
                if(datetime.length > 1){
                    t = new Deadline(deadlineTxt[0].trim(), dt.convertToDateTime(deadlineTxt[1].trim()), true);
                }
                else{
                    t = new Deadline(deadlineTxt[0].trim(), dt.convertToDateTime(deadlineTxt[1].trim()), false);
                }

            }
            else if(commandType.equals("event")){
                dt = new DateTimeParser();
                String[] eventTxt = description.split("/from", 2);
                String eventName = eventTxt[0].trim();
                String[] datetime = eventTxt[1].trim().split("/to", 2);


                t = new Event(eventName, dt.convertToDateTime(datetime[0].trim()), dt.convertToDateTime(datetime[1].trim()));
            }
            ui.showAddMessage();
            tasks.addTask(t);
            ui.showTaskMessage(t, tasks);
            file.saveToFile(tasks);
        } catch(IndexOutOfBoundsException e){
            ui.showDateTimeError();
        }

    }
}

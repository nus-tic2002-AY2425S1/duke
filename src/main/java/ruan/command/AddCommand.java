package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;
import ruan.exception.*;

public class AddCommand extends Command {
    private String type;
    private String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        Task task = null;
        String[] descriptionData = {};

        switch (type) {
            case "todo":
                if (description.isBlank()) {
                    throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
                }

                task = new Todo(description);
                break;
            case "deadline":

                //get different part of command from description
                descriptionData = description.split(" /by ");

                //if description does not have 2 parts, throw error
                if(descriptionData.length != 2){
                    throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
                }

                //if command for description is missing, throw error
                if (descriptionData[0].isBlank()) {
                    throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
                }
                
                //if deadline/by info missing, throw error
                if (descriptionData[1].isBlank()) {
                    throw new RuanException(ErrorType.MISSING_DEADLINE);
                }
                
                task = new Deadline(descriptionData[0].trim(), descriptionData[1].trim());
                break;
            case "event":

                //get different part of command from description
                descriptionData = description.split(" /from | /to ");

                 //if description does not have 3 parts, throw error
                if(descriptionData.length != 3){
                    throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
                }

                //if command for description is missing, throw error
                if (descriptionData[0].isBlank()) {
                    throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
                }
                
                //if time/from/to info missing, throw error
                if (descriptionData[1].isBlank() || descriptionData[2].isBlank()) {
                    throw new RuanException(ErrorType.MISSING_EVENT_TIMES);
                }

                task = new Event(descriptionData[0].trim(), descriptionData[1].trim(), descriptionData[2].trim());
                break;
            default:
                throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
        }

        tasks.addTask(task);

        ui.printTaskAdded(task, tasks.size());

        storage.saveTasks(tasks.getTasks());
    }
}

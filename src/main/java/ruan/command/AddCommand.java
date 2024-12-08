package ruan.command;

import ruan.task.*;
import ruan.ui.Ui;
import ruan.storage.Storage;
import ruan.exception.*;
import ruan.constant.Constants;

/**
 * Represents a command to add task
 * Handles adding Todo, Deadline, and Event tasks to the task list
 */

public class AddCommand extends Command {
    private String type;
    private String description;

    /**
     * Constructs an AddCommand with type and description
     * @param type Type of task to be added (e.g., "todo", "deadline", "event")
     * @param description Details of task including necessary information (e.g., description, deadlines)
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }
    
    /**
     * Executes the add command by creating and adding a new task to the task list
     * Depending on the type, add a Todo, Deadline, or Event task
     * Check if there is duplicate task before adding to tasklist
     * @param tasks Task list where the new task will be added
     * @param ui Ui instance used for interacting with the user/displaying message
     * @param storage Storage instance for saving tasks to a file.
     * @throws RuanException If the input description is incomplete or incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        Task task = null;
        String[] descriptionData = {};

        switch (type) {
            case Constants.TODO_COMMAND:
                if (description.isBlank()) {
                    throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
                }

                task = new Todo(description);
                break;
            case Constants.DEADLINE_COMMAND:
                //get different part of command from description
                descriptionData = description.split(Constants.SPLIT_BY_COMMAND);

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
            case Constants.EVENT_COMMAND:
                //get different part of command from description
                descriptionData = description.split(Constants.SPLIT_FROM_TO_COMMAND);

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

        //check if there is duplicate task
        if (tasks.isDuplicate(task)) {
            throw new RuanException(ErrorType.DUPLICATE_TASK);
        } else {
            // Add the task to the task list
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.size());
            // Save the latest task list
            storage.saveTasks(tasks.getTasks());
        }

    }
}

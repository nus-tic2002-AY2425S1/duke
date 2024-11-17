package jibberJabber.commands;

import jibberJabber.tasks.Task;
import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;
import jibberJabber.ui.Message;
/**
 * The parser class handles parsing and processing of user commands for the task management system.
 */
public class Parser {
    private final TaskList taskList;
    private final TaskFiles taskFiles;
    private final KeywordHandling keywordHandling;
    /**
     * Constructs a Parser object with the specified TaskList and TaskFiles.
     *
     * @param taskList  the TaskList of the array list
     * @param taskFiles the TaskFiles that contain the text file of the task list
     */
    public Parser(TaskList taskList, TaskFiles taskFiles) {
        this.taskList = taskList;
        this.taskFiles = taskFiles;
        this.keywordHandling = new KeywordHandling();
    }
    /**
     * Processes a user keyword command and executes the respective operation.
     *
     * @param todoTask the task information
     * @return false if the command is "bye" (to exit the program), true to continue the program
     */
    public boolean hasProcessCommandSucceed(String todoTask) {
        // Check for empty input string and bypass it
        if (ExceptionHandling.isEmptyInput(todoTask)) {
            Message.printEmptyMessage(false);
            return true;
        }
        String[] splitTodoTask = todoTask.split(" ");
        String splitWord = splitTodoTask[0];
        // Check to ensure the proper keyword is passed since we are checking against ENUM
        if (ExceptionHandling.isInvalidKeywordCommand(splitWord)) {
            Message.printMissingCommandKeywordMessage();
            return true;
        }
        switch (Keywords.valueOf(splitWord.toUpperCase())) {
            case BYE:
                return false;
            case LIST:
                keywordHandling.processListKeyword(taskList);
                return true;
            case COMPLETE:
                keywordHandling.processCompleteTaskWithinPeriod(todoTask.toLowerCase(), taskList);
                return true;
            case FIND:
                keywordHandling.processFindKeyword(todoTask.toLowerCase(), taskList);
                return true;
            case MARK:
                if (splitTodoTask.length < 2) {
                    Message.printEmptyMessage(true);
                    return true;
                }
                keywordHandling.processMarkKeyword(taskList, splitTodoTask[1], true, false);
                taskFiles.writeToTextFile(taskList, taskList.getLastTask(), false);
                return true;
            case UNMARK:
                if (splitTodoTask.length < 2) {
                    Message.printEmptyMessage(true);
                    return true;
                }
                keywordHandling.processMarkKeyword(taskList, splitTodoTask[1], false, false);
                taskFiles.writeToTextFile(taskList, taskList.getLastTask(), false);
                return true;
            case DELETE:
                if (splitTodoTask.length < 2) {
                    Message.printEmptyMessage(true);
                    return true;
                }
                keywordHandling.processRemoveKeyword(taskList, splitTodoTask[1]);
                if (taskList.getTasks().isEmpty()){
                    taskFiles.writeToTextFile(new TaskList(), null, false);
                    return true;
                }
                taskFiles.writeToTextFile(taskList, taskList.getTasks().get(taskList.getTasks().size() - 1), false);
                return true;
            case TODO:
            case DEADLINE:
            case EVENT:
                if (ExceptionHandling.isTaskDuplicated(taskList.getTasks(), todoTask.toLowerCase())) {
                    // Checks for duplicated tasks being added
                    Message.printDuplicateMessage();
                } else {
                    boolean taskAdded = Task.addTask(taskList, todoTask, splitWord, keywordHandling, false);
                    if (!taskList.getTasks().isEmpty() && taskAdded) {
                        taskFiles.writeToTextFile(taskList, taskList.getTasks().get(taskList.getTasks().size() - 1), true);
                    } else {
                        Message.printFailedToAppendToFileMessage();
                    }
                }
                return true;
            default:
                Message.printMissingCommandKeywordMessage();
                return true;
        }
    }
}

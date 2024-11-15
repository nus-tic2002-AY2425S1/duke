package jibberJabber.commands;

import jibberJabber.tasks.Task;
import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;
import jibberJabber.ui.Message;

public class Parser {
    private final TaskList taskList;
    private final TaskFiles taskFiles;
    private final KeywordHandling keywordHandling;

    public Parser(TaskList taskList, TaskFiles taskFiles) {
        this.taskList = taskList;
        this.taskFiles = taskFiles;
        this.keywordHandling = new KeywordHandling();
    }
    public boolean processCommand(String todoTask) {
        // Check for empty input string and bypass it
        if(ExceptionHandling.isEmptyInput(todoTask)){
            Message.printEmptyMessage(false);
            return true;
        }
        String[] splitTodoTask = todoTask.split(" ");
        String splitWord = splitTodoTask[0];
        // User input keyword per instance --> created instance method
        switch (Keywords.valueOf(splitWord.toUpperCase())) {
            case BYE:
                return false;
            case LIST:
                keywordHandling.processListKeyword(taskList);
                return true;
            case MARK:
                keywordHandling.processMarkKeyword(taskList, splitTodoTask[1], true, taskFiles, false);
                return true;
            case UNMARK:
                keywordHandling.processMarkKeyword(taskList, splitTodoTask[1], false, taskFiles, false);
                taskFiles.writeToTextFile(taskList, taskList.getLastTask(), false);
                return true;
            case DELETE:
                keywordHandling.processRemoveKeyword(taskList, splitTodoTask[1], taskFiles);
                taskFiles.writeToTextFile(taskList, taskList.getTasks().get(taskList.getTasks().size() - 1), false);
                return true;
            case TODO:
            case DEADLINE:
            case EVENT:
                if (ExceptionHandling.isTaskDuplicated(taskList.getTasks() ,todoTask)) {
                    // Checks for duplicated tasks being added
                    Message.printDuplicateMessage();
                } else {
                    Task.addTask(taskList, todoTask, splitWord, keywordHandling, false);
                    taskFiles.writeToTextFile(taskList, taskList.getTasks().get(taskList.getTasks().size() - 1), true);
                }
                return true;
            default:
                Message.printMissingCommandKeywordMessage();
                return true;
        }
    }
}

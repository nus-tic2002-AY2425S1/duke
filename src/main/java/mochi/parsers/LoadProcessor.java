package mochi.parsers;

import mochi.commands.AddTaskCommand;
import mochi.commands.Command;
import mochi.common.DialogMessages;
import mochi.common.exception.MochiException;
import mochi.tasks.*;

public class LoadProcessor {
    private final TaskList taskList;

    public LoadProcessor(TaskList taskList) {
        this.taskList = taskList;
    }

    public void processInput(String input, String delimiter) throws MochiException {
        // need to escape | character
        if (delimiter.equals("||"))
            delimiter = "\\|\\|";
        String[] token = input.split(delimiter);
        Command cmd;
        TaskType taskTypes = TaskType.getValue(token[0]);
        switch (taskTypes) {
            case T:
                String t_name = token[2];
                cmd = new AddTaskCommand(taskList, new Todo(t_name), Boolean.parseBoolean(token[1]));
                break;
            case D:
                String d_name = token[2];
                String by = token[3];
                cmd = new AddTaskCommand(taskList, new Deadline(d_name, by), Boolean.parseBoolean(token[1]));
                break;
            case E:
                String e_name = token[2];
                String from = token[3];
                String to = token[4];
                cmd = new AddTaskCommand(taskList, new Event(e_name, from, to), Boolean.parseBoolean(token[1]));
                break;
            default:
                throw new MochiException(DialogMessages.INPUT_UNKNOWN.getValue());
        }
        cmd.execute();
    }
}

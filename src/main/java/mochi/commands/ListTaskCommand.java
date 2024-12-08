package mochi.commands;

import mochi.common.DialogMessages;
import mochi.common.Utils;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

import java.util.Objects;

public class ListTaskCommand extends Command {
    private final String[] token;

    public ListTaskCommand(TaskList taskList, String[] token) {
        super(taskList);
        this.token = token;
    }

    @Override
    public void execute() {
        if (token.length == 1) {
            taskList.printTaskList();
        } else if (token.length == 2) {
            if ("event".equals(token[1])) {
                taskList.printTaskList("E", "", "");
            } else if ("deadline".equals(token[1])) {
                taskList.printTaskList("D", "", "");
            } else if ("todo".equals(token[1])) {
                taskList.printTaskList("T", "", "");
            }
        } else {
            String tmpDate = "";
            if (Objects.equals(token[2], "/before")) {
                tmpDate = Utils.trimStringArrayWithStartEnd(token, "/before", "", " ");
            }
            if (Objects.equals(token[2], "/after")) {
                tmpDate = Utils.trimStringArrayWithStartEnd(token, "/after", "", " ");
            }
            String type = token[1];
            String op = token[2];
            if ("event".equals(type)) {
                taskList.printTaskList("E", op, tmpDate);
            } else if ("deadline".equals(type)) {
                taskList.printTaskList("D", op, tmpDate);
            } else {
                Ui.response(DialogMessages.LIST_TASK_EMPTY.getValue());
            }
        }
    }
}
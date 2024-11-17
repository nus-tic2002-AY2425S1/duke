package josbot.ui;

import josbot.common.Messages;
import josbot.parser.DateTimeParser;
import josbot.task.Task;
import josbot.task.TaskList;
import java.util.Scanner;

public class UI {

    private static final String LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showGreeting(String type) {
        switch (type) {
        case "Start":
            showLine();
            DateTimeParser dt = new DateTimeParser();
            System.out.println(dt.getTimeGreeting() + ", "+Messages.START_GREETING);
            showLine();
            break;
        case "End":
            System.out.println(Messages.END_GREETING);
            showLine();
            break;
        }
    }

    public void showError(String errorType) {
        switch (errorType) {
        case "invalid_command":
            System.out.println(Messages.ERROR_INVALID_COMMAND);
            break;
        case "invalid_datetime_format":
            System.out.println(Messages.ERROR_INVALID_DATETIME_FORMAT);
            break;
        case "invalid_tag":
            System.out.println(Messages.ERROR_INVALID_TAG);
            break;
        case "missing_description":
            System.out.println(Messages.ERROR_MISSING_DESCRIPTION);
            break;
        case "missing_mark_number":
            System.out.println(Messages.ERROR_MISSING_MARK_NUMBER);
            break;
        case "file_corrupted":
            System.out.println(Messages.ERROR_FILE_CORRUPTED);
            break;
        case "loading_error":
            System.out.println(Messages.ERROR_LOADING);
            showLine();
            break;
        case "file_not_found_error":
            System.out.println(Messages.ERROR_FILE_NOT_FOUND);
            showLine();
        case "invalid_list":
            System.out.println(Messages.ERROR_INVALID_LIST);
            break;
        default:
            System.out.println("Unknown Error : " + errorType);
        }
    }

public void showInvalidDateTime() {
    System.out.println(Messages.ERROR_INVALID_DATETIME);
}

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    public void showListMessage() {
        System.out.println(Messages.LIST_MESSAGES);
    }

    public void showHelp(){
        System.out.println(Messages.HELP_MESSAGES);
    }

    public void showReminderMessage() {
        System.out.println(Messages.REMINDER_MESSAGES);
    }

    public void showAddMessage() {
        System.out.println(Messages.ADD_MESSAGES);
    }

    public void showTaskMessage(Task t, TaskList list) {
        System.out.println(t.toString());
        System.out.println(Messages.TASK_MESSAGES_START + list.getTaskCount() + Messages.TASK_MESSAGES_END);
    }

    public void showMarkMessage(Task t, Boolean marked) {
        if (marked) {
            System.out.println(Messages.MARK_MESSAGES);
        } else {
            System.out.println(Messages.UNMARK_MESSAGES);
        }
        System.out.println(t.toString());
    }

    public void showTagMessage(Task t, Boolean tagged) {
        if (tagged) {
            System.out.println(Messages.TAG_MESSAGES);
        } else {
            System.out.println(Messages.UNTAG_MESSAGES);
        }
        System.out.println(t.toString());
    }

    public void showDeleteMessage(String message, int count) {
        if (message.equals("start")) {
            System.out.println(Messages.DELETE_MESSAGES);
        } else {
            System.out.println(Messages.TASK_MESSAGES_START + count + Messages.TASK_MESSAGES_END);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showDateTimeError() {
        System.out.println(Messages.ERROR_DATEITME);
    }



    public void showIndexOutofBoundError() {
        System.out.println(Messages.ERROR_INDEX_OUT_OF_BOUND);
    }

    public void showNumberFormatError() {
        System.out.println(Messages.ERROR_NUMBER_FORMAT);
    }

    public void showTaskLists(TaskList tasks, boolean showNumber) {

        if (tasks.getTaskCount() == 0) {
            System.out.println(Messages.NO_TASK_FOUND_MESSAGES);
        } else {
            if (showNumber) {
                for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
                    System.out.println(i + ". " + tasks.getTasks().get(i - 1).toString());
                }
            } else {
                for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
                    System.out.println(tasks.getTasks().get(i - 1).toString());
                }
            }
            System.out.println(Messages.TASK_LIST_MESSAGES_START + tasks.getTaskCount() + Messages.TASK_LIST_MESSAGES_END);
        }
    }
}

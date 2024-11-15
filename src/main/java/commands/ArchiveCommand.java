package commands;

import common.Constants;
import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";
    private static final String ALL = "all";
    public static final String MESSAGE_USAGE = COMMAND_WORD + Constants.SPACE + ALL;
    private static final String NOTED = "Noted.";
    private static final String ALL_TASKS = "All" + SPACE + Constants.TASK + Constants.S;
    private static final String HAS = "has";
    private static final String HAVE = "have";
    private static final String MESSAGE_SUCCESS_POST = "been successfully archived to";
    private static final String MESSAGE_SUCCESS_ALL_POST = ALL_TASKS + SPACE + HAVE + SPACE + MESSAGE_SUCCESS_POST;

    private final String target;

    public ArchiveCommand(String target) {
        assert target != null : "Target must not be null";
        assert target.equalsIgnoreCase(ALL) || target.matches("\\d+") : "Target must be \"all\" or a valid task index";
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    // implement in tasklist
    public boolean archiveAll() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        assertExecuteParams(taskList, ui, storage);
        String target = getTarget();
        String[] messages = null;

        switch (target) {
        case ALL:
            storage.archiveTasks(taskList);
            messages = new String[]{MESSAGE_SUCCESS_ALL_POST + SPACE +
                storage.getArchiveTasksFilePath() + Constants.DOT};
            break;

        default:
            int targetNumber = Integer.parseInt(target);

            Task task = taskList.getTaskForOperation(targetNumber);
            assert task != null : "Task must exist for the given target number";

            storage.archiveTask(task);

            String MESSAGE_SUCCESS = NOTED + SPACE + Messages.THE_TASK + SPACE +
                Constants.BACKTICK + task + Constants.BACKTICK + SPACE + HAS + SPACE +
                MESSAGE_SUCCESS_POST + SPACE + storage.getArchiveTasksFilePath() + Constants.DOT;
            messages = new String[]{MESSAGE_SUCCESS};

            break;
        }

        ui.printMessage(messages);
    }

}

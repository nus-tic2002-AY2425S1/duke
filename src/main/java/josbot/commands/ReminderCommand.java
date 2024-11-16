package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


/**
 * This Command is initialised when user executes the 'reminder' command or when the program first launched
 */

public class ReminderCommand extends Command {

    public ReminderCommand() {
    }

    public ReminderCommand(String commandType, String description) {
        super(commandType, description);
    }

    /**
     * Execute command for 'reminder'
     * It will list down the list of Deadline task that
     * are not marked as 'Done'
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui    A class to generate messages
     * @param file  It stores the filepath and method related to storing and saving of the file
     */

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) {
        ui.showReminderMessage();
        ui.showTaskLists(sortTaskDate(tasks), false);
    }


    /**
     * Returns a list of deadline task
     * that are not 'done'
     *
     * @param tasks takes in the current TaskList
     * @return List of deadline task that are not marked as 'done'
     */
    private List<Task> getDeadlineTasks(TaskList tasks) {
        //TaskList deadlineTasks = new TaskList();
        List<Task> deadlineTasks = new ArrayList<Task>();
        for (Task task : tasks.getTasks()) {
            if (task.getType().equals("D") && !task.getStatusIcon().equals("X")) {
                deadlineTasks.add(task);
            }
        }
        return deadlineTasks;
    }

    /**
     * Returns a TaskList that's sorted based on the date from oldest (top) to newest (bottom).
     * The data used is based on provided tasklist
     *
     * @param tasks
     * @return sorted deadline tasklist
     */
    public TaskList sortTaskDate(TaskList tasks) {
        TaskList sortedTasks = new TaskList();
        if (tasks != null) {
            List<Task> sortedList = getDeadlineTasks(tasks);

            //Solution below adapted from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
            Collections.sort(sortedList, new Comparator<Task>() {
                @Override
                public int compare(Task t1, Task t2) {
                    return t1.getDateTime().compareTo(t2.getDateTime());
                }
            });
            for (Task task : sortedList) {
                sortedTasks.addTask(task);
            }

        }
        return sortedTasks;
    }
}

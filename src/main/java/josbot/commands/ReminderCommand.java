package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

import java.util.*;

public class ReminderCommand extends Command {
    public ReminderCommand() {

    }

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException {
        ui.showReminderMessage();
        ui.showTaskLists(sortTaskDate(tasks), false);
    }

    private List<Task> getDeadlineTasks(TaskList tasks) {
        //TaskList deadlineTasks = new TaskList();
        List<Task> deadlineTasks = new ArrayList<Task>();
        for (Task task : tasks.getTasks()) {
            if(task.getType().equals("D") && !task.getStatusIcon().equals("X"))
            {
                deadlineTasks.add(task);
            }
        }
        return deadlineTasks;
    }

    /**
     *
     * Used to sort the task list date based on provided tasklist and return back a sorted tasklist
     *
     * @param tasks
     * @return sorted deadline tasklist
     */
    public TaskList sortTaskDate(TaskList tasks){
        //TaskList sortedTasks = getDeadlineTasks(tasks);
        List<Task> sortedList = getDeadlineTasks(tasks);

        //Solution below adapted from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
        Collections.sort(sortedList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDateTime().compareTo(t2.getDateTime());
            }
        });

        TaskList sortedTasks = new TaskList();
        for(Task task : sortedList){
            sortedTasks.addTask(task);
        }

        return sortedTasks;
    }
}

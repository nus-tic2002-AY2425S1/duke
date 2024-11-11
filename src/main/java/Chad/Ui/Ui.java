package Chad.Ui;

import Chad.TaskList.Task;
import Chad.TaskList.TaskList;

public interface Ui {


    String showWelcome();

    //String readCommand();

    void showMsg(String msg);

    void showError(String errmsg);

    void showBye();

    void showTaskList(TaskList tasks);

    void showDeleteTask(Task task, int noOfTask);

    void showAddTask(Task task, int noOfTask);

    void showMarkTask(Task task);

    void showUnMarkTask(Task task);

    void showHelp(String helpContent);
}
package Chad.Ui;
import Chad.TaskList.*;

public interface Ui {

    static  void showMsg(String myMsg){
        System.out.println(myMsg);
    }
    void showWelcome();
    String readCommand();
    void showLine();
    void showError(String errmsg);
    void showBye();
    void showTaskList(TaskList tasks);
    void showDeleteTask(Task task,int noOfTask);
    void showAddTask(Task task,int noOfTask);
    void showMarkTask(Task task);
    void showUnMarkTask(Task task);
}
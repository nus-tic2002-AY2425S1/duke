import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
  protected ArrayList<Task> _list = new ArrayList<>();
  protected HashMap<String,Integer> _taskNameToIndexMap = new HashMap<>();

  public TaskList() {}
  public void addTask(String task) {
    Task tmp = new Task(task);
    _list.add(tmp);
    _taskNameToIndexMap.put(task,_list.indexOf(tmp));
  }

  public ArrayList<Task> getTaskList() {
    return _list;
  }

  public void markTask(int index) {
    _list.get(index).markTask();
  }

  public void unmarkTask(int index) {
    _list.get(index).unmarkTask();
  }
  public boolean getTaskStatusByName(String name) {
    return (_list.get(_taskNameToIndexMap.get(name)).getStatus());
  }
  public boolean getTaskStatusByIndex(int index) {
    return (_list.get(index).getStatus());
  }
  public String getTaskNameByIndex(int index) {
    return (_list.get(index).getName());
  }
  public void printTaskList() {
    System.out.println("____________________________________________________________");
    System.out.println(" Here are the task in your list:");
    for (Task i : _list) {
      System.out.println(_list.indexOf(i)+1 + ".[" + i.getStatusIcon() + "] " + i.getName());
    }
    System.out.println("____________________________________________________________");
  }
}

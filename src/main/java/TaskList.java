import java.util.ArrayList;
import java.util.HashSet;

public class TaskList {
  private static final ArrayList<String> _list = new ArrayList<>();
  private static final HashSet<String> _status = new HashSet<>();
  public TaskList() {}
  public void addTask(String task) {
    _list.add(task);
  }

  public ArrayList<String> getTaskList() {
    return _list;
  }

  public void markTask(int index) {
    _status.add(_list.get(index - 1));
  }

  public void unmarkTask(int index) {
    _status.remove(_list.get(index-1));
  }
  public boolean getTaskStatusByName(String name) {
    return (_status.contains(name));
  }
  public boolean getTaskStatusByIndex(int index) {
    return (_status.contains(_list.get(index-1)));
  }
  public String getTaskNameByIndex(int index) {
    return (_list.get(index-1));
  }
  public void printTaskList() {
    System.out.println("____________________________________________________________");
    System.out.println(" Here are the task in your list:");
    int index = 0;
    for (String i : _list) {
      String statusPrint = " ";
      if (_status.contains(i))
        statusPrint = "X";
      index++;
      System.out.println(index + ".[" + statusPrint + "] " + i);
    }
    System.out.println("____________________________________________________________");
  }
}

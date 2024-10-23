import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
  protected ArrayList<Task> _list = new ArrayList<>();
  protected HashMap<String,Integer> _taskNameToIndexMap = new HashMap<>();

  public TaskList() {}
  public void addTask(Task task) throws MochiException {
    // if new
    if (_taskNameToIndexMap.get(task.getName()) != null && _taskNameToIndexMap.get(task.getName()) != -1 ) {
      throw new MochiException(ExceptionMessages.TASK_EXIST);
    }
    if (task.getName().isEmpty()) {
      throw new MochiException(ExceptionMessages.TASK_NAME_EMPTY);
    }
    if (task instanceof Event eventT) {
      if (eventT.getFrom().isEmpty()) {
        throw new MochiException(ExceptionMessages.TASK_EVENT_FROM_EMPTY);
      }
      if (eventT.getTo().isEmpty()) {
        throw new MochiException(ExceptionMessages.TASK_EVENT_TO_EMPTY);
      }
    }
    if (task instanceof Deadline deadlineT) {
      if (deadlineT.getBy().isEmpty()) {
        throw new MochiException(ExceptionMessages.TASK_DEADLINE_BY_EMPTY);
      }
    }
    _list.add(task);
    _taskNameToIndexMap.put(task.getName(),_list.indexOf(task));
  }

  public ArrayList<Task> getTaskList() {
    return _list;
  }

  public void markTask(int index) {
    _list.get(index).markTask();
  }

  public void deleteTask(int index) {
    _taskNameToIndexMap.replace(_list.remove(index).getName(),-1);
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
  public String getTaskStatusIconByIndex(int index) {
    return (_list.get(index).getStatusIcon());
  }
  public String getTaskNameByIndex(int index) {
    return (_list.get(index).getName());
  }
  public Task getTaskById(int index) {
    return _list.get(index);
  }
  public int getTaskListSize() {
    return _list.size();
  }
  public void printTaskList() {
    System.out.println("____________________________________________________________");
    System.out.println(DialogMessages.LIST_TASK.getValue());
    for (Task i : _list) {
      System.out.println(_list.indexOf(i)+1 + "." + i.toString());
    }
    System.out.println("____________________________________________________________");
  }
}

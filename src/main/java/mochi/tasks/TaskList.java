package mochi.tasks;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import mochi.common.exception.*;
import mochi.parsers.*;
import mochi.storage.*;
import mochi.ui.*;
import mochi.common.*;

public class TaskList implements AutoCloseable {
  protected ArrayList<Task> _list = new ArrayList<>();
  protected HashMap<String,Integer> _taskNameToIndexMap = new HashMap<>();
  final static String _saveDelimiter = "||";
  private SaveManager saveManger;
  public TaskList() {
    try {
      saveManger = new SaveManager();
      loadTasks();
    } catch (Exception e) {
      Ui.response(e.getMessage());
    }
  }
  @Override
  public void close() {
    try {
      saveTasks();
    } catch (Exception e) {
      Ui.response(e.getMessage());
    }
  }
    private void saveTasks() throws MochiException {
    ArrayList<String> tasksToDbString = new ArrayList<>();
    for (Task task : _list) {
      tasksToDbString.add(task.toDBString());
    }
    try {
      saveManger.save(tasksToDbString);
    } catch (Exception e) {
      Ui.response(e.getMessage());
    }
  }
  private void loadTasks() throws MochiException {
    try {
      ArrayList<String> tmp = saveManger.load();
      if (tmp == null) {
        Ui.response(DialogMessages.SAVE_TASK_NOT_FOUND.getValue());
      } else {
        int line = 1;
        for (String s : tmp) {
          if (!s.contains(_saveDelimiter)) {
            throw new MochiException(DialogMessages.LOAD_TASK_LINE_ERROR.getValue() + line);
          }
          else {
            LoadProcessor loadProcessor = new LoadProcessor(this);
            loadProcessor.processInput(s,_saveDelimiter);
            line++;
          }
        }
        Ui.response(DialogMessages.SAVE_TASK_FOUND.getValue());
      }
    } catch (IOException e) {
      Ui.response(e.getMessage());
    } catch (MochiException e) {
      Ui.response(DialogMessages.LOAD_TASK_ERROR.getValue());
    }
  }
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
    saveTasks();
  }

  public ArrayList<Task> getTaskList() {
    return _list;
  }

  public void markTask(int index) throws MochiException {
    _list.get(index).markTask();
    saveTasks();
  }

  public void deleteTask(int index) throws MochiException{
    _taskNameToIndexMap.replace(_list.remove(index).getName(),-1);
    saveTasks();
  }

  public void unmarkTask(int index) throws MochiException {
    _list.get(index).unmarkTask();
    saveTasks();
  }
  public boolean getTaskStatusByName(String name) {
    return (_list.get(_taskNameToIndexMap.get(name)).getStatus());
  }
  public Integer getTaskIdByTask(Task task) {
    return _taskNameToIndexMap.get(task.getName());
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

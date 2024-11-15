package mochi.tasks;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import mochi.common.exception.*;
import mochi.parsers.*;
import mochi.storage.*;
import mochi.ui.*;
import mochi.common.*;
/**
 * TaskList manages a collection of Task objects, providing functionality to add, delete, mark,
 * unmark, and retrieve tasks. It also handles loading tasks from and saving tasks to a file.
 */
public class TaskList implements AutoCloseable {
  protected ArrayList<Task> _list = new ArrayList<>();
  protected HashMap<String,Integer> _taskNameToIndexMap = new HashMap<>();
  final static String _saveDelimiter = "||";
  private SaveManager saveManger;
  /**
   * Constructs a TaskList instance and loads tasks from the default save file.
   */
  public TaskList() {
    try {
      saveManger = new SaveManager();
      loadTasks();
    } catch (Exception e) {
      Ui.response(e.getMessage());
    }
  }
  /**
   * Closes the TaskList, saving the tasks to the save file.
   */
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
  /**
   * Adds a task to the TaskList if it is valid and does not already exist.
   *
   * @param task the Task to add.
   * @throws MochiException if the task already exists or has invalid fields.
   */
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

  /**
   * Marks a task as completed based on its index.
   *
   * @param index the index of the task to mark.
   * @throws MochiException if marking fails.
   */
  public void markTask(int index) throws MochiException {
    _list.get(index).markTask();
    saveTasks();
  }
  /**
   * Deletes a task based on its index.
   *
   * @param index the index of the task to delete.
   * @throws MochiException if deletion fails.
   */
  public void deleteTask(int index) throws MochiException{
    _taskNameToIndexMap.replace(_list.remove(index).getName(),-1);
    saveTasks();
  }

  public void massDeleteTask(int[] index) throws MochiException{
    Arrays.sort(index);
    for (int i = index.length - 1; i >= 0; i--) {
      _taskNameToIndexMap.replace(_list.remove(index[i]-1).getName(),-1);
    }
    saveTasks();
  }
  /**
   * Unmarks a task as completed based on its index.
   *
   * @param index the index of the task to unmark.
   * @throws MochiException if unmarking fails.
   */
  public void unmarkTask(int index) throws MochiException {
    _list.get(index).unmarkTask();
    saveTasks();
  }
  /**
   * Retrieves the ID (index) of a task by its instance.
   *
   * @param task the task instance.
   * @return the index of the task in the list.
   */
  public Integer getTaskIdByTask(Task task) {
    return _taskNameToIndexMap.get(task.getName());
  }
  public Task getTaskById(int index) {
    return _list.get(index);
  }
  public int getTaskListSize() {
    return _list.size();
  }
  /**
   * Prints the list of tasks to the console.
   */
  public void printTaskList() {

    System.out.println(DialogMessages.LIST_TASK.getValue());
    for (Task i : _list) {
      System.out.println(_list.indexOf(i)+1 + "." + i.toString());
    }
    Ui.printDivier();
  }
  /**
   * Prints a filtered list of tasks based on type, operator, and date.
   *
   * @param type the type of tasks to filter.
   * @param op   the comparison operator (e.g., "/before" or "/after").
   * @param date the date for filtering tasks.
   */
  public void printTaskList(String type, String op, String date) {
    Ui.printDivier();
    if (op.isEmpty()) {
      printTaskList(type);
    } else {
      System.out.println(DialogMessages.LIST_TASK_FILTERED.getValue());
      for (Task i : _list) {
        if (Objects.equals(i.getType(), type)) {
          if (i.compare(op, date)) {
            System.out.println(_list.indexOf(i) + 1 + "." + i.toString());
          }
        }
      }
    }
    Ui.printDivier();
  }
  public void printTaskList(String type) {
    System.out.println(DialogMessages.LIST_TASK_FILTERED.getValue());
    for (Task i : _list) {
      if (Objects.equals(i.getType(), type)) {
        System.out.println(_list.indexOf(i)+1 + "." + i.toString());
      }
    }
  }
}

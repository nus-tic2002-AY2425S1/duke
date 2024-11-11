package util;

import java.time.LocalDateTime;

import task.Todo;
import task.Deadline;
import task.Event;
import task.TaskList;

// Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/util/TypicalPersons.java
public class TypicalTasks {

    public Todo todo_doHomework = new Todo("Do homework", true);
    public Todo todo_buyGroceries = new Todo("Buy groceries", false);
    public Todo todo_washDishes = new Todo("Wash dishes", true);

    public Deadline deadline_submitReport = new Deadline("Submit report", false, LocalDateTime.of(2021, 4, 24, 14, 33));
    public Deadline deadline_resolveCustomerTicket = 
        new Deadline("Resolve customer ticket", true, LocalDateTime.of(2022, 8, 4, 9, 15));
    public Deadline deadline_finalizeGuestList = 
        new Deadline("Finalize guest list", false, LocalDateTime.of(2021, 6, 24, 14, 33));

    public Event event_projectLaunch = 
        new Event("Project launch", true, LocalDateTime.of(2024, 11, 9, 14, 30), LocalDateTime.of(2026, 12, 25, 9, 0));
    public Event event_birthday = 
        new Event("Birthday", false, LocalDateTime.of(2024, 11, 9, 18, 45), LocalDateTime.of(2024, 11, 9, 22, 45));
    public Event event_anniversary = 
        new Event("Anniversary", true, LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2025, 1, 1, 23, 59));
    
    public TaskList initDefaultTaskList() {
        TaskList defaultTaskList = new TaskList();

        defaultTaskList.addTask(todo_doHomework);           // Index 0
        defaultTaskList.addTask(todo_buyGroceries);         // Index 1
        // defaultTaskList.addTask(todo_washDishes);

        defaultTaskList.addTask(deadline_submitReport);     // Index 2
        defaultTaskList.addTask(deadline_resolveCustomerTicket);        // Index 3
        // defaultTaskList.addTask(deadline_finalizeGuestList);

        defaultTaskList.addTask(event_projectLaunch);       // Index 4
        defaultTaskList.addTask(event_birthday);            // Index 5
        defaultTaskList.addTask(event_anniversary);

        return defaultTaskList;
    }

}

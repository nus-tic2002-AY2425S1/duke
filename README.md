# WKDuke Project

This is a project for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are
instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project
   first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions) as explained
   in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/wkduke/WKDuke.java` file, right-click it, and choose `Run WKDuke.main()` (if
   the code
   editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the
   below as the output:
   ```
    ____________________________________________________________________________
           +----------------------------------------------------------+
           |                                                          |
           |                 WKDuke Pre-initialisation                |
           |                                                          |
           |   Please enter the custom file path to load your tasks   |
           |           (relative or full path) or press 'Enter'       |
           |     to use the default data source (./data/tasks.txt).   |
           |                                                          |
           +----------------------------------------------------------+
      Please enter your filepath: 
   ```

---

## Overview

WKDuke is a task management application designed for the command-line interface. It allows users to manage tasks
such as Todos, Deadlines, and Events efficiently. The application supports features like task prioritization, flexible
sorting, duplicate task detection, and customizable data sources for better task management.

---

## Features

- **Manage Tasks**: Add, delete, and update tasks.
- **Flexible Sorting and Filtering**: Organize tasks by type, priority, or date.
- **Custom Data Source**: Specify a custom file to store your tasks or use the default storage.
- **Comprehensive Help System**: Get detailed guidance on each command and resolve issues easily.

---

## Commands

WKDuke supports the following commands:

| Command                                               | Description                                   | Example                                                      |
|-------------------------------------------------------|-----------------------------------------------|--------------------------------------------------------------|
| `list`                                                | Lists all tasks in your task list.            | `list`                                                       |
| `list /on {datetime}`                                 | Lists tasks on a specific date.               | `list /on 2024-11-05`                                        |
| `find {keywords}`                                     | Finds tasks matching the specified keywords.  | `find book, meeting`                                         |
| `todo {description}`                                  | Adds a new Todo task.                         | `todo Read a book`                                           |
| `deadline {description} /by {datetime}`               | Adds a Deadline task with a due date.         | `deadline Submit report /by 2024-11-05 23:59`                |
| `event {description} /from {datetime} /to {datetime}` | Adds an Event task with a start and end date. | `event Workshop /from 2024-11-05 09:00 /to 2024-11-05 17:00` |
| `delete {task-numbers}`                               | Deletes task(s) by their numbers.             | `delete 1, 3, 5`                                             |
| `mark {task-numbers}`                                 | Marks task(s) as done.                        | `mark 1, 2`                                                  |
| `unmark {task-numbers}`                               | Marks task(s) as not done.                    | `unmark 1, 2`                                                |
| `sort /by {type,priority,datetime} /order {asc,desc}` | Sorts tasks by type, priority, or datetime.   | `sort /by priority /order asc`                               |
| `update-priority {task-number} {priority}`            | Updates the priority of a specific task.      | `update-priority 1 H`                                        |
| `bye`                                                 | Exits the application.                        | `bye`                                                        |
| `help`                                                | Displays help information for commands.       | `help`                                                       |

---

## Tips

WKDuke uses the following notation:

| Symbol | Description      |
|--------|------------------|
| `[T]`  | Todo Task        |
| `[D]`  | Deadline Task    |
| `[E]`  | Event Task       |
| `[H]`  | High Priority    |
| `[M]`  | Medium Priority  |
| `[L]`  | Low Priority     |
| `[X]`  | Task is Done     |
| `[ ]`  | Task is Not Done |

---
package Chad.Command;

public enum HelpType {
    GENERAL("This is a general help message."),
    TODO("How to use: todo <task_description> "+System.lineSeparator()+ "- Adds a task to the list."),
    DEADLINE("How to use: deadline <task_description> /by <date> "+System.lineSeparator()+ " Adds a deadline task."),
    EVENT("How to use event: event <task_description> /from <start_time> /to <end_time> "+System.lineSeparator()+ " Adds an event task."),
    FIND("How to use find :find <find_index>; "+System.lineSeparator()+ "Find a task by searching for a keyword in the task description."),
    LIST("How to use: list [<date>] "+System.lineSeparator()+ " Lists all tasks; optionally filter by date."),
    MARK("How to use: mark <task_index> "+System.lineSeparator()+ " Marks a task as done."),
    UNMARK("How to use: unmark <task_index> "+System.lineSeparator()+ " Marks a task as not done."),
    DELETE("How to use: delete <task_index> "+System.lineSeparator()+ " Removes a task from the list."),
    EXIT("How to use: bye "+System.lineSeparator()+ " Exits the application.");

    private final String message;

    HelpType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

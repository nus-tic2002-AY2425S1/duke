package Chad.Command;

public enum HelpType {
    GENERAL("This is a general help message."),
    TODO("How to use: todo <task_description> " + System.lineSeparator() + "- Adds a task to the list."),
    DEADLINE("How to use: deadline <task_description> /by <date> " + System.lineSeparator() + " Adds a deadline task."),
    EVENT("How to use event: event <task_description> /from <start_time> /to <end_time> " + System.lineSeparator() + " Adds an event task."),
    FIND("How to use find :find <find_index>; " + System.lineSeparator() + "Find a task by searching for a keyword in the task description."),
    SUMMARY("How to use summary :summary <time_period>; " + System.lineSeparator() + "statistics about the items managed by the App e.g., " +
            "show the number of tasks that have been completed in the past 1 week." + System.lineSeparator() + "period format:1 week, 3 days, 2 weeks"),
    LIST("How to use: list [<date>] " + System.lineSeparator() + " Lists all tasks; optionally filter by date."),
    MARK("How to use: mark <task_index> " + System.lineSeparator() + " Marks a task as done."),
    UNMARK("How to use: unmark <task_index> " + System.lineSeparator() + " Marks a task as not done."),
    DELETE("How to use: delete <task_index> " + System.lineSeparator() + " Removes a task from the list."),
    EXIT("How to use: bye " + System.lineSeparator() + " Exits the application.");

    private final String message;

    HelpType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

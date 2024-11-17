package KLBot.Ui;

import KLBot.TaskList.TaskList;

public class Ui {

    private static final String botName = "KLbot";

    public void greetUser() {
        printLine();
        // Symbols are taken from :https://www.htmlsymbols.xyz/
        System.out.println("Hey there! I'm " + botName + ", your cheerful helper buddy! 😊");
        System.out.println("Here's a quick guide to help you get started: 🎉");
        System.out.println("\t✅ To mark a task as completed or undo it, just type 'mark' or 'unmark' followed by the task number. For example: 'mark 1'");
        System.out.println("\t📚 To add a ToDo task, type 'todo' followed by your task description. For example: 'todo borrow a book'");
        System.out.println("\t📅 To add an Event task, type 'event' followed by the event details and date/time. For example: 'event project meeting /from 2023-10-15 14:00 /to 2023-10-15 16:00'");
        System.out.println("\t⏰ To add a Deadline task, type 'deadline' followed by your task description and the deadline date. For example: 'deadline return book /by 2023-10-15' or deadline return book /by Sun");
        System.out.println("\t\uD83D\uDD0E To search for a task, type 'search' followed by a keyword.For example: 'search return book'");
        System.out.println("What can I do for you today? (Type 'bye' when you're ready to say goodbye! 😢)");
        printLine();
    }

    public void sayGoodbye() {
        printLine();
        System.out.println("Aww, you're leaving? It was so nice chatting with you! Take care and see you soon! 💖");
        printLine();
    }

    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("It looks like your task list is empty! No worries, just add some tasks and I'll be happy to help you manage them! 😊");
            return;
        }

        printLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.getTask(i));
        }
        printLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(String taskDescription) {
        System.out.println("Got it! I've added this task to your list: " + taskDescription);
    }

    public void showTaskRemoved(String taskDescription) {
        System.out.println("Alrighty! I’ve successfully removed this task: " + taskDescription);
    }

    public void showTaskMarked() {
        System.out.println("Yay! You've successfully marked this task as done! 🎉");
    }

    public void showTaskUnmarked() {
        System.out.println("Alright, I've marked this task as not done yet. You're doing great! 💪");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}


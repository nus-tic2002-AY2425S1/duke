public class UIUtil {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printGreeting() {
        String logo = """
            ____
            |  _ \\  ___ _ __  _ __  _   _
            | | | |/ _ \\ '_ \\| '_ \\| | | |
            | |_| |  __/ | | | | | | |_| |
            |____/ \\___|_| |_|_| |_|\\__, |
                                    |___/
            """;
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Denny");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printListTasksHeader() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Here are the tasks in your list:");
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskMarkedDone(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskUnmarked(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printError(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Oops! " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printUnknownCommand(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Sorry, I didn't understand that command. " + message);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskDeletedMessage(Task deletedTask, int remainingTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + deletedTask);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTasksLoaded(int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" " + taskCount + " task(s) loaded from storage.");
        System.out.println(HORIZONTAL_LINE);
    }
}

public class UIUtil {

    public static void printGreeting() {
        String logo = "____                         \n"
                + "|  _ \\  ___ _ __  _ __  _   _ \n"
                + "| | | |/ _ \\ '_ \\| '_ \\| | | |\n"
                + "| |_| |  __/ | | | | | | |_| |\n"
                + "|____/ \\___|_| |_|_| |_|\\__, |\n"
                + "                        |___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Denny");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printListTasksHeader() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public static void printTaskMarkedDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public static void printTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public static void printError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" Oops! " + message);
        System.out.println("____________________________________________________________");
    }

    public static void printUnknownCommand(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" Sorry, I didn't understand that command. " + message);
        System.out.println("____________________________________________________________");
    }

    public static void printTaskDeletedMessage(Task deletedTask, int remainingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + deletedTask);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

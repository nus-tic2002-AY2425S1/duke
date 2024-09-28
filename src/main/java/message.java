public class message {

    public static void printHorizontalLines(){
        System.out.print("____________________________________________________________\n");
    }
    public static void printEndingMessage(){
        System.out.print("Bye. Hope to see you again soon!\n");
        printHorizontalLines();
    }
    /* Level-0 printing simple welcome message */
    public static void printWelcomeMessage(String systemName) {
        printHorizontalLines();
        System.out.println("Hello! I'm " + systemName);
        System.out.println("What can I do for you?");
        printHorizontalLines();
    }
}

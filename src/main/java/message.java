public class message {

    private String textMessage;

    public message(String textMessage) {
        this.textMessage = textMessage;
    }
    public String getTextMessage() {
        return textMessage;
    }
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
    public static void printHorizontalLines(){
        System.out.println("____________________________________________________________");
    }
    // (Wk 3) Level-0 Print welcome message
    public static void printWelcomeMessage(String systemName) {
        printHorizontalLines();
        System.out.println("Hello! I'm " + systemName);
        System.out.println("What can I do for you?");
        printHorizontalLines();
    }
    public static void printEndingMessage(){
        printHorizontalLines();
        System.out.print("Bye. Hope to see you again soon!\n");
        printHorizontalLines();
    }
}

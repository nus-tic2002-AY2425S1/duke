public class PistaMint {
    public static int length=50;
    public static String line="-".repeat(length);
    public static void greetings(){
        System.out.println(line+"\nNi Hao! I'm PistaMint\nWhat can I do for you?\n"+line);

    }
    public static void exit(){
        System.out.println("Xie Xie! Hope to see you again soon~\n"+line);
    }
    public static void main(String[] args) {
        greetings();
        exit();
    }
}

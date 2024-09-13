import java.util.Arrays;
import java.util.Scanner;

public class PistaMint {
    public static int length = 50;
    public static String line = "-".repeat(length);
    public static String input="";
    public static String[] listItems=new String[100];
    public static int itemCount=0;
    public static void greetings() {
        System.out.println("\t"+line + "\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?\n\t" + line);
    }

    public static void exit() {
        System.out.println("\t"+line+"\n\tXie Xie! Hope to see you again soon~\n\t" + line);
    }

    public static void echo(String input) {
        if(input.equalsIgnoreCase("bye")){
            return;
        }
        System.out.println("\t"+line+"\n\tadded: "+input+"\n\t"+line);
    }
    public static String[] addList(String item){
        listItems[itemCount]=item;
        itemCount++;
        return Arrays.copyOf(listItems,itemCount);
    }

    public static void printItems(){
        System.out.println("\t"+line);
        for(int i=0;i<itemCount;i++){
            System.out.println("\t"+i+". "+listItems[i]);
        }
        System.out.println("\t"+line);
    }

    public static void main(String[] args) {
        greetings();
        while (!input.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if(input.equalsIgnoreCase("list")) {
                printItems();
            }
            else{
                echo(input);
                addList(input);
            }

        }
        exit();
    }
}

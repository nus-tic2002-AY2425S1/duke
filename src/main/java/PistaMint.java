import java.util.Arrays;
import java.util.Scanner;

public class PistaMint {
    public static int length = 50;
    public static String line = "-".repeat(length);
    public static String input="";
    public static String[] listItems=new String[100];
    public static Task[] taskList=new Task[100];
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
        ;
        System.out.println("\t"+line+"\n\tadded: "+input+"\n\t"+line);
    }
    public static Task[] addList(Task t){
        taskList[itemCount]=t;
        itemCount++;
        return Arrays.copyOf(taskList,itemCount);
    }

    public static void printItems(){
        System.out.println("\t"+line);
        for(int i=0;i<itemCount;i++){
            System.out.println("\t"+(i+1)+".["+taskList[i].getStatusIcon()+"] "+taskList[i].getDescription());
        }
        System.out.println("\t"+line);
    }
    public static boolean indexOutofRange(int index){
        if (index > itemCount){
            System.out.println("\t"+line);
            System.out.println("\tItem is out of range. You currently only have "+itemCount+" items.");
            System.out.println("\t"+line);
            return true;
        }
        return false;
    }
    public static void markTask(String word, int index) {
        if (word.equalsIgnoreCase("mark") ) {
            if(!(indexOutofRange(index))) {
                taskList[index - 1].markAsDone();
                System.out.println("\t" + line);
                System.out.println("\tNice! I've marked this task as done:\n\t[" + taskList[index - 1].getStatusIcon() + "] " + taskList[index - 1].getDescription());
                System.out.println("\t" + line);
            }
        }
        else{
            if(!(indexOutofRange(index))) {
                taskList[index-1].markAsUnDone();
                System.out.println("\t" + line);
                System.out.println("\tOK, I've marked this task as not done yet!\n\t[" + taskList[index-1].getStatusIcon() + "] " + taskList[index-1].getDescription());
                System.out.println("\t" + line);
            }
        }
    }
    public static void main(String[] args) {
        greetings();
        while (!input.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            String words[]=input.split(" ");
            if(words[0].equalsIgnoreCase("list")) {
                printItems();
            } else if (words[0].equalsIgnoreCase("mark")) {
                markTask(words[0], Integer.parseInt(words[1]));
            } else if (words[0].equalsIgnoreCase("unmark")) {
                markTask(words[0], Integer.parseInt(words[1]));
            } else{
                echo(input);
                Task task=new Task(input);
                addList(task);
            }

        }
        exit();
    }
}

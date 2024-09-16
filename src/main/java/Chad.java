import java.util.Scanner;
import java.util.Arrays;


public class Chad {

    static Task[] inputList = new Task[100]; 
    static int NoOfTask;

    public static void processCmd(String cmdString)
    {
        String arr[] = cmdString.split(" ");

        switch(arr[0]){

            case "list":
            //print chad's recorded task list
            printList();

            break;

            /* to add: what if user input mark index out of range */
            case "mark":
            //mark a task with its index to is done
            inputList[Integer.parseInt(arr[1])-1].setTask();
            chadSay("Nice! I've marked this task as done:" + System.lineSeparator() + inputList[Integer.parseInt(arr[1])-1].getStatusIcon()+inputList[Integer.parseInt(arr[1])-1].description);

            break;
            case "unmark":
            //mark a task with its index to not done
            inputList[Integer.parseInt(arr[1])-1].unSetTask();
            chadSay("OK, I've marked this task as not done yet:" + System.lineSeparator() + inputList[Integer.parseInt(arr[1])-1].getStatusIcon()+inputList[Integer.parseInt(arr[1])-1].description);
            break;

            default:
            //default case
            break;

        }
        return;

    }

    public static void chadSay(String chadSay)
    {
        String myline = "_________________________________________________________________";
        System.out.println(myline);
        System.out.println(chadSay);
        System.out.println(myline);
        return;
    }

    public static void printList(){

        // TODO: add your code here
        String myline = "_________________________________________________________________";    
        System.out.println(myline);
        System.out.println("Here are the tasks in your list:");
        
        for(int i=0;i<99;i++)
        {
            if(inputList[i]==null)
            {
                //record this i as no of task.
                NoOfTask=i+1;
                break;
            }
            System.out.println((i+1)+"."+inputList[i].getStatusIcon()+inputList[i].getDescription());
        }
        System.out.println(myline);
        return;
    }

    public static void main(String[] args) {

        String myline = "_________________________________________________________________";
        String logo = " Chad\n";
        chadSay("Hello from " + logo+"\n"+"What can I do for you?\n");
        Task[] inputList = new Task[100];
        String[] chadCommands = {"list","mark","unmark"};
        //Arrays.asList(yourArray).contains(yourValue)
        //https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java

        String line;
        int i=0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        
        
        while(!line.equals("bye"))
        {
            //group key words to be processed in function instead of use nested if loop
            //key words : list, set, unset

            //commands may be of format set sth, get set from input line
            String arr[] = line.split(" ");
            //arr[0] is the command if user input is a command
            boolean isCommand = Arrays.asList(chadCommands).contains(arr[0]);
            if(isCommand)
            {
                //printList(inputList);
                processCmd(line);
                line=in.nextLine();
                continue;
            }
            

            chadSay("added: " + line);

            Chad.inputList[i]=new Task(line);
            i++;
            line=in.nextLine();
            
        }
        
        chadSay("Bye. Hope to see you again soon!");
    }
}

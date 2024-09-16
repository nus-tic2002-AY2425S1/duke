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
            int taskIdx=Integer.parseInt(arr[1])-1;

            if(taskIdx>NoOfTask)
            {
                //throw some exception here
            }

            inputList[taskIdx].setTask();
            chadSay("Nice! I've marked this task as done:" + System.lineSeparator() + inputList[taskIdx].toString());

            break;

            case "unmark":
            //mark a task with its index to not done
            int unmarkTaskIdx=Integer.parseInt(arr[1])-1;
            if(unmarkTaskIdx>NoOfTask)
            {
                //throw some exception here
            }

            inputList[unmarkTaskIdx].unSetTask();
            chadSay("OK, I've marked this task as not done yet:" + System.lineSeparator() + inputList[unmarkTaskIdx].toString());
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
        
        for(int i=0;i<NoOfTask;i++)
        {
            if(inputList[i]==null)
            {
                break;
            }
            System.out.println((i+1)+"."+inputList[i].toString());
        }
        System.out.println(myline);
        return;
    }

    public static void main(String[] args) {

        String myline = "_________________________________________________________________";
        String logo = " Chad\n";
        chadSay("Hello from " + logo+"\n"+"What can I do for you?\n");
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
            
            //according to different task type add to task list.

            switch(arr[0])
            {
                case "todo":
                //add todo, case sensitive not handled
                String todoname = line.split("todo")[1];
                Chad.inputList[i]=new Todo(todoname);
                break;

                case "deadline":
                //add deadline
                String deadlinename,deadlineby;
                deadlinename = line.substring(line.indexOf("deadline") + 8, line.indexOf("/by"));////space is taken into consideration, to allow space index change to +7
                deadlineby=line.split("/by")[1];
                Chad.inputList[i]=new Deadline(deadlinename,deadlineby);

                break;

                case "event":
                //add event
                String eventname,startsAt,endsAt;
                eventname=line.substring(line.indexOf("event") + 6, line.indexOf("/from")); //space is taken into consideration, to allow space index change to +5
                startsAt = line.substring(line.indexOf("/from") + 6, line.indexOf("/to"));//space is taken into consideration, to allow space index change to +5
                endsAt=line.split("/to")[1];
                Chad.inputList[i]=new Event(eventname,startsAt,endsAt);
                break;

                default:
                //add general task
                Chad.inputList[i]=new Task(line);
                break;
            }
            i++;
            NoOfTask=i;

            chadSay( "Got it. I've added this task:" + System.lineSeparator()+inputList[i-1].toString() +System.lineSeparator() +"Now you have "+ NoOfTask+" tasks in the list.");
            /*todo, deadline, event 
             * task : task name
             * todo : todo + space + task name
             * deadline: deadline + task name + "/by" + by
             * event: event + task name + "/from" + startsAt + "/to" + endsAt
            */


            line=in.nextLine();
            
        }
        
        chadSay("Bye. Hope to see you again soon!");
    }
}

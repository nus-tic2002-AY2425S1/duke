import java.util.Scanner;
import java.util.Arrays;


public class Chad {

    static Task[] inputList = new Task[100]; 
    static int NoOfTask;

    public static void processCmd(String cmdString) throws ChadException
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

            if(inputList[taskIdx]==null)
            {
                //throw ProcessCmdFailException
                //bot say:suggest use list to view task's id
                throw new ChadException("OOPS!!! Invalid task id to mark");
            }

            inputList[taskIdx].setTask();
            chadSay("Nice! I've marked this task as done:" + System.lineSeparator() + inputList[taskIdx].toString());

            break;

            case "unmark":
            //mark a task with its index to not done
            int unmarkTaskIdx=Integer.parseInt(arr[1])-1;
            if(unmarkTaskIdx<1 || inputList[unmarkTaskIdx]==null)
            {
                //throw ProcessCmdFailException
                throw new ChadException("OOPS!!! Invalid task id to unmark");
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
        String addTaskManual = "You can add task by: Taskname1\n"
                                +"add todo by:todo Mytodo1\n"
                                +"add deadline by:deadline myDeadLineName1 /by sometime1\n"
                                +"add event by: event MyEventname1 /from time1 /to time2\n";
        String myline = "_________________________________________________________________";    
        System.out.println(myline);
        //check if the task list is empty
        if(inputList[0]==null)
        {
            System.out.println("Your tasks list is empty");
            System.out.println(addTaskManual);
            System.out.println(myline);
            return;
        }
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

    public static void addTask(String taskString,int taskID) throws ChadException
    {
        String arr[] = taskString.split(" ");


        switch(arr[0])
            {
                case "todo":
                //add todo, case sensitive not handled
                try{
                    String todoname = taskString.split("todo")[1];
                    Chad.inputList[taskID]=new Todo(todoname);

                }catch (ArrayIndexOutOfBoundsException e)
                {
                    throw new ChadException("Opps!Pls re-write discription for todo");
                }
                
          
                break;

                case "deadline":
                //add deadline

                String deadlinename,deadlineby;
                try{
                    deadlinename = taskString.substring(taskString.indexOf("deadline") + 8, taskString.indexOf("/by"));////space is taken into consideration, to allow space index change to +7
                    deadlineby=taskString.split("/by")[1];
                    Chad.inputList[taskID]=new Deadline(deadlinename,deadlineby);
                }catch(StringIndexOutOfBoundsException e){
                    throw new ChadException("Opps!! Add deadline failed!!!Deadline Syntax: deadline task1 /by time1");
                }
                //deadlinename = taskString.substring(taskString.indexOf("deadline") + 8, taskString.indexOf("/by"));////space is taken into consideration, to allow space index change to +7
                
                
                

                break;

                case "event":
                //add event
                String eventname,startsAt,endsAt;
                try{
                eventname=taskString.substring(taskString.indexOf("event") + 6, taskString.indexOf("/from")); //space is taken into consideration, to allow space index change to +5
                startsAt = taskString.substring(taskString.indexOf("/from") + 6, taskString.indexOf("/to"));//space is taken into consideration, to allow space index change to +5
                endsAt=taskString.split("/to")[1];
                Chad.inputList[taskID]=new Event(eventname,startsAt,endsAt);
                }catch(StringIndexOutOfBoundsException e){
                    throw new ChadException("Opps!! Add event failed!!!Event Syntax: event task1 /from time1 /to time2");
                }
                
                break;

                default:
                //add general task
                //all other string will add as a whole as a new task
                Chad.inputList[taskID]=new Task(taskString);
                break;
            }

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
                //printList(inputList);S
                try{
                    processCmd(line);
                } catch(ChadException e)
                {
                    System.out.println(e);  
                }
                
                line=in.nextLine();
                continue;
            }
            
            //according to different task type add to task list.
            /*
             * Put addlist in separate function to enable exception handle
             * try call somefunction() 
             * catch exception when have..
             * 
             * 1.need to re-define function, to follow syntax ... throws exception1,exception2 ...  
             * 2. figure out what exception may happen
             *      -add to list,  invaild format,input string not following example syntax
             *      -print list,   list is empty?(not a exception, just tell user the list is empty)
             *      -processing command mark/unmark with invalid task index, 
             * 3. add custom exception class, add custom error msg
             * 4. caller part, add try-catch implementation
             * 
             */
            try{
                addTask(line,i);
                i++;
                NoOfTask=i;
                chadSay( "Got it. I've added this task:" + System.lineSeparator()+inputList[i-1].toString() +System.lineSeparator() +"Now you have "+ NoOfTask+" tasks in the list.");

            }catch (ChadException e){
                //do sth here
                System.out.println(e);  
            }
            
            

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

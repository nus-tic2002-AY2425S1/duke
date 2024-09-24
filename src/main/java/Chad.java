import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class Chad {

    //static Task[] inputList = new Task[100]; 
    static ArrayList<Task> inputList =  new ArrayList<>();
    
    static Integer NoOfTask;

    public static String splitStringFirst(String delimiter,String str){

        //cut input string into 2 parts, eg str = "abcde fghi", delimiter ="f"
        //return second part ghi
        int index = str.indexOf(delimiter);

        String firstPart = str.substring(0, index);
        //String secondPart = str.substring(index + delimiter.length());
        //System.out.println("First Part: " + firstPart);
        return firstPart;
    }

    public static String splitStringSecond(String delimiter,String str){

        //cut input string into 2 parts, eg str = "abcde fghi", delimiter ="f"
        //return second part ghi
        int index = str.indexOf(delimiter);

        //String firstPart = str.substring(0, index);
        String secondPart = str.substring(index + delimiter.length());
        //System.out.println("Second Part: " + secondPart);
        return secondPart;
    }

    public static void markTask(Integer taskID){

        inputList.get(taskID).setTask();
        chadSay("Nice! I've marked this task as done:" + System.lineSeparator() + inputList.get(taskID).toString());

    }

    public static void unmarkTask(Integer taskID){
        inputList.get(taskID).unSetTask();
        chadSay("OK, I've marked this task as not done yet:" + System.lineSeparator() + inputList.get(taskID).toString());

    }
    public static void deleteTask(Integer TaskID){
        String deletedTask=inputList.get(TaskID).toString();
        inputList.remove(inputList.get(TaskID));
        NoOfTask--;
        //     Noted. I've removed this task:
       //[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
       //Now you have 4 tasks in the list.
        chadSay("Noted. I've removed this task: " +System.lineSeparator()+ deletedTask+System.lineSeparator() + "Now you have " + NoOfTask+" tasks in the list.");
    }

    public static void processCmd(String cmdString) throws ChadException,ArrayIndexOutOfBoundsException
    {
        String arr[] = cmdString.split(" ");
        Integer taskId=-1;
        boolean isList = arr[0].equals("list");
        // the parseInt function may throw exception, caller function should catch this exception and show chadException
        if(!isList)
        {
            taskId = Integer.parseInt(arr[1])-1;
        }
        
        if(!isList&&(taskId < 0 || taskId > NoOfTask -1))
        {
            throw new ChadException("OOPS!!! Invalid task ID");
        }

        switch(arr[0]){

            case "list":
            //print chad's recorded task list
            printList();

            break;

            /* to add: what if user input mark index out of range */
            case "mark":
            markTask(taskId); 
            break;

            case "unmark":
            //mark a task with its index to not done
            unmarkTask(taskId);
            break;

            case "delete":
            deleteTask(taskId);
            break;

            default:
            //default cases
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
        if(NoOfTask.equals(0))
        {
            System.out.println("Your tasks list is empty");
            System.out.println(addTaskManual);
            System.out.println(myline);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        
        //no of task range from 1 to NoOfTask+1
        for(int i=0;i<NoOfTask;i++)
        {
            System.out.println((i+1)+"."+inputList.get(i).toString());
        }
        System.out.println(myline);
        return;
    }

    public static void addTask(String taskString,int taskID) throws ChadException
    {
            /*todo, deadline, event 
             * task : task name
             * todo : todo + space + task name
             * deadline: deadline + task name + "/by" + by
             * event: event + task name + "/from" + startsAt + "/to" + endsAt
            */
        String arr[] = taskString.split(" ");


        switch(arr[0])
            {
                case "todo":
                //add todo, case sensitive not handled
                try{
                    //String todoname = taskString.split("todo")[1];
                    //String todoname = splitStringSecond("todo ",taskString);
                    String todoname = splitStringSecond("todo ",taskString);
                    Todo addTodo=new Todo(todoname);
                    Chad.inputList.add(addTodo);

                }
                catch (StringIndexOutOfBoundsException e)
                {
                    //this exception happens when there is no item after "todo "
                    throw new ChadException("Opps!Pls enter name for todo");
                }
                
          
                break;

                case "deadline":
                //add deadline
                //deadline: deadline + task name + "/by" + by

                String deadlinename,deadlineby;
                try{
                    deadlineby=taskString.split("/by")[1];
                    deadlinename = splitStringFirst("/by",splitStringSecond("deadline ", taskString));
                    //deadlinename = taskString.substring(taskString.indexOf("deadline") + 8, taskString.indexOf("/by"));////space is taken into consideration, to allow space index change to +7
                    
                    Deadline addDL=new Deadline(deadlinename,deadlineby);
                    Chad.inputList.add(addDL);
                }catch(StringIndexOutOfBoundsException e){
                    //detect error for deadlinename = splitStringSecond("/by",splitStringFirst("deadline ", taskString));
                    //invalid deadline name
                    //error case: deadline /by tmr (without task name)
                    throw new ChadException("Opps!!! Invalid Deadline Name!Syntax: deadline deadname1 /by time1");
                }
                catch(ArrayIndexOutOfBoundsException e){
                    //detect error for line deadlineby=taskString.split("/by")[1];
                    //invalid deadline time
                    throw new ChadException("Opps!!Invalid Deadline Time!Syntax: deadline deadname1 /by time1");
                }
                
                
                
                

                break;

                case "event":
                //add event
                //event: event + task name + "/from" + startsAt + "/to" + endsAt
                String eventname,startsAt,endsAt;
                try{

                eventname =splitStringSecond("event ",splitStringFirst("/from", taskString));
                startsAt =splitStringSecond("/from ",splitStringFirst("/to", taskString));
                //eventname=taskString.substring(taskString.indexOf("event") + 6, taskString.indexOf("/from")); //space is taken into consideration, to allow space index change to +5
                //startsAt = taskString.substring(taskString.indexOf("/from") + 6, taskString.indexOf("/to"));//space is taken into consideration, to allow space index change to +5
                endsAt=taskString.split("/to")[1];
                Event addE = new Event(eventname,startsAt,endsAt);
                Chad.inputList.add(addE);
                //Chad.inputList[taskID]=new Event(eventname,startsAt,endsAt);
                }catch(StringIndexOutOfBoundsException e){

                    throw new ChadException("Opps!! Add event failed!!!Event Syntax: event task1 /from time1 /to time2");
                }
                
                break;

                default:
                //add general task
                //all other string will add as a whole as a new task
                Task addT = new Task(taskString);
                Chad.inputList.add(addT);
                //Chad.inputList[taskID]=new Task(taskString);
                break;
            }

    }
    public static void main(String[] args) {

        String myline = "_________________________________________________________________";
        String logo = " Chad\n";
        chadSay("Hello from " + logo+"\n"+"What can I do for you?\n");
        //String[] chadCommands = {"list","mark","unmark"};

        String line;
        // when ever starts the program, No of task starts from 0. need to change later when load from file
        NoOfTask=0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        
        
        while(!line.equals("bye"))
        {
            //group key words to be processed in function instead of use nested if loop
            //key words : list, set, unset

            //commands may be of format set sth, get set from input line
            String arr[] = line.split(" ");
            //arr[0] is the command if user input is a command
            boolean isCommand = ChadCmd.contains(arr[0]);
           
            if(isCommand)
            {
                try{
                    processCmd(line);
                } catch(ChadException e)
                {
                    System.out.println(e);  
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Opps!! Pls enter Task Id! ");
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

                addTask(line,NoOfTask);
                String taskDespt = inputList.get(NoOfTask).toString();
                NoOfTask++;
                
                chadSay( "Got it. I've added this task:" + System.lineSeparator()+taskDespt +System.lineSeparator() +"Now you have "+ NoOfTask+" tasks in the list.");

            }catch (ChadException e){
                //do sth here
                System.out.println(e);  
            }


            line=in.nextLine();
            
        }
        
        chadSay("Bye. Hope to see you again soon!");
    }
}

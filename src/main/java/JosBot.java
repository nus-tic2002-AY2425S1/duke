import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class JosBot {

    public static void printInfo(String type){
        switch(type){
            case "Dash":
                System.out.print("____________________________________________________________\n");
                break;
            case "Start":
                printInfo("Dash");
                System.out.println("Hello I'm JosBot\nWhat can I do for you?");
                printInfo("Dash");
                break;
            case "End":
                printInfo("Dash");
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
        }

    }

    public static String parseListToString(ArrayList<Task> file_list)
    {
        String list_string = "";
        for(int i = 0; i < file_list.size(); i++)
        {
            list_string += file_list.get(i).getType() + ",";
            if(file_list.get(i).getStatusIcon().equals("X"))
            {
                list_string += "1,";
            }
            else
            {
                list_string += "0,";
            }
            list_string += file_list.get(i).getDescription();

            //deadline
            if(file_list.get(i).getType().equals("D"))
            {
               Deadline d = (Deadline) file_list.get(i);
               list_string += ","+d.getBy();
            }
            else if(file_list.get(i).getType().equals("E"))
            {
                Event e = (Event) file_list.get(i);
                list_string += ","+e.getFrom() + "," + e.getTo();
            }

            list_string += "\n";
        }
        //System.out.println(list_string);
        return list_string;

    }

    public static ArrayList<Task> loadFromFile(String filepath) throws FileNotFoundException {
        ArrayList<Task> load_list = new ArrayList<>();

        File f = new File(filepath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] line = s.nextLine().split(",");
            //System.out.println(line);
            Task t = null;

            if(line[0].equals("T"))
            {
                t = new Todo(line[2]);
            }
            else if(line[0].equals("D"))
            {
                t = new Deadline(line[2], line[3]);
            }
            else if(line[0].equals("E"))
            {
                t = new Event(line[2], line[3], line[4]);
            }

            if(line[1].equals("1"))
            {
                t.markAsDone();
            }

            load_list.add(t);
        }

        return load_list;
    }

    public static void saveToFile(String list_string) {
        String filepath = "data/JosBotList.txt";
        File f = new File(filepath);
        //System.out.println("full path: " + f.getAbsolutePath());
        try{
            if(f.exists()){
                FileWriter fw = new FileWriter(filepath);
                fw.write(list_string);
                fw.close();
            }
            else
            {
                throw new JosBotException("");
            }
        }
        catch(Exception e){
            System.out.println("Error : No save file was found!");
        }
    }

    public static void main(String[] args) {

        printInfo("Start");
        ArrayList<Task> list = new ArrayList<>();
        int list_count = 0;
        try{
            list = loadFromFile("data/JosBotList.txt");
            list_count = list.size();
        }
        catch(Exception e){
            System.out.println("Error : No save file was found!");
        }


        //load function

        Scanner in = new Scanner(System.in);
        String user_input = in.nextLine().trim();


        while(!user_input.equals("bye"))
        {
            try {
                printInfo("Dash");

                if (user_input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < list_count + 1; i++) {
                        //System.out.println(i + ". " + list[i - 1].toString());
                        System.out.println(i + ". " + list.get(i - 1).toString());
                    }
                } else if (user_input.startsWith("mark") || user_input.startsWith("unmark")) {
                   //to check if current list is not empty
                    if(list_count > 0)
                    {
                        Integer last_digit = 0;
                        if (user_input.startsWith("mark")) {
                            last_digit = Integer.valueOf(user_input.substring(4).trim()) - 1;
                            System.out.println("Nice! I've marked this task as done:");
                            //list[last_digit].markAsDone();
                            list.get(last_digit).markAsDone();
                        } else if (user_input.startsWith("unmark")) {
                            last_digit = Integer.valueOf(user_input.substring(6).trim()) - 1;
                            System.out.println("OK, I've marked this task as not done yet:");
                            //list[last_digit].markAsNotDone();
                            list.get(last_digit).markAsNotDone();
                        }
                        //System.out.println("[" + list[last_digit].getStatusIcon() + "] " + list[last_digit].getDescription());
                        //System.out.println("[" + list.get(last_digit).getStatusIcon() + "] " + list.get(last_digit).getDescription());
                        System.out.println(list.get(last_digit).toString());
                        //save function
                        saveToFile(parseListToString(list));
                    }
                    else
                    {
                        //print error
                        throw new JosBotException("missing_list");
                    }
                } else if (user_input.startsWith("todo") || user_input.startsWith("deadline") || user_input.startsWith("event")) {
                    //to get the first word
                    String[] fw = user_input.split(" ");
                    if(fw.length > 1)
                    {
                        System.out.println("Got it. I've added this task:");
                        if (user_input.startsWith("todo")) {
                            String todo_txt = user_input.replace("todo ", "");
                            Task t = new Todo(todo_txt);
                            //list[list_count] = t;
                            list.add(t);
                            list_count++;
                            System.out.println(t.toString());

                        } else if (user_input.startsWith("deadline")) {
                            String deadline_txt = user_input.replace("deadline ", "");
                            String[] split_txt = deadline_txt.split("/by", 2);
                            deadline_txt = split_txt[0].trim();
                            //task name and deadline day
                            Task t = new Deadline(split_txt[0].trim(), split_txt[1].trim());
                            //list[list_count] = t;
                            list.add(t);
                            list_count++;
                            System.out.println(t.toString());
                        } else if (user_input.startsWith("event")) {
                            String event_txt = user_input.replace("event ", "");
                            String[] split_txt = event_txt.split("/", 3);
                            String event_name = split_txt[0].trim();
                            String event_from = split_txt[1].replace("from ", "").trim();
                            String event_to = split_txt[2].replace("to ", "").trim();
                            Task t = new Event(event_name, event_from, event_to);
                            //list[list_count] = t;
                            list.add(t);
                            list_count++;
                            System.out.println(t.toString());
                        }

                        System.out.println("Now you have " + list_count + " task in the list.");
                        //save function
                        saveToFile(parseListToString(list));
                    }
                    else
                    {
                        throw new JosBotException("missing_description");
                    }

                }
                else if (user_input.startsWith("delete"))
                {
                    String[] fw = user_input.split(" ");
                    if(fw.length > 1)
                    {
                        System.out.println("Noted. I've removed this task:");
                        Integer last_digit = 0;
                        last_digit = Integer.valueOf(user_input.substring(6).trim()) - 1;
                        System.out.println(list.get(last_digit).toString());
                        list.remove(last_digit);
                        list_count--;
                        System.out.println("Now you have " + list_count + " tasks in the list.");
                        //save function
                        saveToFile(parseListToString(list));
                    }
                    else
                    {
                        throw new JosBotException("missing_description");
                    }
                }
                else {
                    throw new JosBotException("missing_task");
                }
                //end of try
            } catch (JosBotException e)
            {
                String error_message = "";

                switch (e.getMessage()){
                    case "missing_task":
                        error_message = "No Task Found!";
                        break;
                    case "missing_list":
                        error_message = "There is no available task currently! Please try to add a task before marking them";
                        break;
                    case "missing_description":
                        error_message = "The description of the task cannot be empty! Please add a description to your task";
                        break;
                    case "missing_file":
                        error_message = "Save file cannot be found!";
                        break;
                    default:
                        error_message = "unknown error";
                }

                System.out.println("Error : " + error_message);

            }

            printInfo("Dash");
            user_input = in.nextLine().trim();
        }
        printInfo("End");

    }
}

package josbot.storage;

import josbot.*;
import josbot.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStorage {

    protected String path;

    public FileStorage(String filePath) {
        this.path = filePath;
    }

    public ArrayList<Task> load() throws JosBotException, FileNotFoundException {
        ArrayList<Task> load_list = new ArrayList<>();

        File f = new File(path); // create a File for the given file path
        //System.out.println("Path : " + f.getAbsolutePath());
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

    public void saveToFile(TaskList tasks) {
        //String filepath = "data/JosBotList.txt";
        String list_string = parseListToString(tasks.getTasks());
        File f = new File(path);
        //System.out.println("full path: " + f.getAbsolutePath());
        try{
            if(f.exists()){
                FileWriter fw = new FileWriter(path);
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
}

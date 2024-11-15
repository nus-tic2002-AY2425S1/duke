package josbot.storage;

import josbot.*;
import josbot.parser.DateTimeParser;
import josbot.task.*;
import josbot.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStorage {

    protected String path;
    private DateTimeParser dt;

    public FileStorage(String filePath) {
        this.path = filePath;
    }

    private void checkFilePath() throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            UI ui = new UI();
            ui.showFileNotFoundError();
            file.createNewFile();
        }
    }


    /**
     *
     * Used to load specific file and return the content as ArrayList<Task>
     * This method is usually triggered when the program first launches
     *
     * @return ArrayList<Task>
     * @throws JosBotException when there are JosBotException error coming from convertDateTime method
     * @throws FileNotFoundException when file is not found in the specified file path
     */
    public ArrayList<Task> load() throws JosBotException, FileNotFoundException, IOException {
        checkFilePath();
        File f = new File(path);

        ArrayList<Task> load_list = new ArrayList<>();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            dt = new DateTimeParser();
            String user_input = s.nextLine();
            String tag = "";
            if(user_input.contains("#"))
            {
                tag = user_input.split("#")[1];
                user_input = user_input.split("#")[0];
            }
            String[] line = user_input.split(",");
            Task t = null;

            if(line[0].equals("T"))
            {
                t = new Todo(line[2]);
            }
            else if(line[0].equals("D"))
            {
                if(line.length == 5)
                {
                    t = new Deadline(line[2], dt.convertToDateTime(line[3]+" "+line[4]), true);
                }
                else
                {
                    t = new Deadline(line[2], dt.convertToDateTime(line[3]));
                }

            }
            else if(line[0].equals("E"))
            {
                t = new Event(line[2], dt.convertToDateTime(line[3]), dt.convertToDateTime(line[3]));
            }

            if(line[1].equals("1"))
            {
                t.markAsDone();
            }

            if(!tag.equals(""))
            {
                t.setTag(tag);
            }
            else {
                t.setTag("");
            }

            load_list.add(t);
        }

        return load_list;
    }


    /**
     *
     * Used to save TaskList in a filepath declared
     *
     * @param tasks
     */
    public void saveToFile(TaskList tasks) {
        String list_string = parseListToString(tasks.getTasks());
        File f = new File(path);
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


    private static String parseListToString(ArrayList<Task> file_list)
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
                DateTimeParser dt_parser = new DateTimeParser();
                list_string += ","+dt_parser.convertToString(d.getDateTime(),"store");
            }
            else if(file_list.get(i).getType().equals("E"))
            {
                Event e = (Event) file_list.get(i);
                DateTimeParser dt_parser = new DateTimeParser();
                list_string += ","+dt_parser.convertToString(e.getFrom(),"store") + "," + dt_parser.convertToString(e.getTo(),"store");
            }

            if(!file_list.get(i).getTag().equals(""))
            {
                list_string += "#"+file_list.get(i).getTag();
            }

            list_string += "\n";
        }
        return list_string;
    }
}

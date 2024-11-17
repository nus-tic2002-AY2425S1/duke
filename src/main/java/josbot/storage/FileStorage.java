package josbot.storage;

import josbot.JosBotException;
import josbot.parser.DateTimeParser;
import josbot.parser.StorageParser;
import josbot.task.Deadline;
import josbot.task.Event;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.task.Todo;
import josbot.ui.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStorage {

    protected static String path;
    private static DateTimeParser dt;

    public FileStorage(String filePath) {
        this.path = filePath;
    }

    /**
     * Returns ArrayList of Task by loading the specific file content
     * This method is usually triggered when the program first launches
     *
     * @return ArrayList<Task>
     * @throws JosBotException       when there are JosBotException error coming from convertDateTime method
     */
    public static ArrayList<Task> load() throws JosBotException, IOException, ArrayIndexOutOfBoundsException {
        File f = new File(path);
        f.createNewFile();
        ArrayList<Task> loadList = new ArrayList<>();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        try {
            while (s.hasNext()) {
                dt = new DateTimeParser();
                String userInput = s.nextLine();
                String tag = "";
                if (userInput.contains("#")) {
                    tag = userInput.split("#")[1];
                    userInput = userInput.split("#")[0];
                }
                String[] line = userInput.split(",");
                Task t = null;

                if (line[0].equals("T")) {
                    t = new Todo(line[2]);
                } else if (line[0].equals("D")) {
                    if (line.length == 5) {
                        t = new Deadline(line[2], dt.convertToDateTime(line[3] + " " + line[4]), true);
                    } else {
                        t = new Deadline(line[2], dt.convertToDateTime(line[3]));
                    }

                } else if (line[0].equals("E")) {
                    t = new Event(line[2], dt.convertToDateTime(line[3]), dt.convertToDateTime(line[5]));
                } else {
                    throw new JosBotException("");
                }

                if (line[1].equals("1")) {
                    t.markAsDone();
                }

                if (!tag.equals("")) {
                    t.setTag(tag);
                } else {
                    t.setTag("");
                }

                loadList.add(t);
            }

        } catch (ArrayIndexOutOfBoundsException | JosBotException e) {
            UI ui = new UI();
            s.close();
            Path filepath = Paths.get(path);
            Files.delete(filepath);
            ui.showLine();
            ui.showError("file_corrupted");
            f.createNewFile();
            return new ArrayList<Task>();
        }
        return loadList;
    }

    /**
     * Save TaskList in a filepath declared
     *
     * @param tasks contains all the current tasks
     */
    public static void saveToFile(TaskList tasks) {
        StorageParser parser = new StorageParser();
        String listString = parser.parseListToString(tasks.getTasks());
        File f = new File(path);
        try {
            if (f.exists()) {
                FileWriter fw = new FileWriter(path);
                fw.write(listString);
                fw.close();
            } else {
                throw new JosBotException("");
            }
        } catch (Exception e) {
            System.out.println("Error : No save file was found!");
        }
    }
}

package Pistamint;
import Pistamint.General.Task;
import Pistamint.TaskList.TaskList;
import Pistamint.Ui.Ui;
import Pistamint.Storage.Storage;
import Pistamint.Parser.Parser;

import java.io.FileNotFoundException;
import java.util.*;

public class PistaMint {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    public static int length = 50;
    public static String line = "-".repeat(length);
    public static String input = "";
    public static String directoryPath = "data";
    public static String filePath = directoryPath + "/duke.txt";
    public PistaMint(String filePath) throws FileNotFoundException {
        storage=new Storage(directoryPath,filePath);
        tasks=new TaskList(storage);
        storage.load();

    }

    /**
     * Shows the greeting to user, read the user and calls for the Parser to parse and process the commands
     */
    public void run() {
        Ui.showGreeting();
        Scanner scanner=new Scanner(System.in);
        while(!input.equalsIgnoreCase("bye")){
            input = scanner.nextLine();
            Parser.parseCommand(input);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Storage storage=new Storage(directoryPath,filePath);
        TaskList taskList = new TaskList(storage);   // Instantiate Pistamint.TaskList
        Parser parser = new Parser(taskList);
        Ui ui = new Ui(taskList);
        new PistaMint("./data/duke.txt").run();
    }
}

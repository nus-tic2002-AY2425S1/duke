import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Javaro {
    
    private Ui ui;

    public Javaro(String filePath) {
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        try {
            String fullCommand = ui.readCommand();
            ui.showLine();
        } finally {
            ui.showLine();
        }
        

        // while (!isExit) {
        //     try {
        //         String fullCommand = ui.readCommand();
        //         ui.showLine(); // show the divider line ("_______")
        //         Command c = Parser.parse(fullCommand);
        //         c.execute(tasks, ui, storage);
        //         isExit = c.isExit();
        //     } catch (DukeException e) {
        //         ui.showError(e.getMessage());
        //     } finally {
        //         ui.showLine();
        //     }
        // }
        
    }

    public static void main(String[] args) {
        new Javaro("./data/tasks.txt").run();
    }
}

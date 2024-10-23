package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

    private String fileName;

    public ReadFromFile(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> readTasks() throws FileNotFoundException {
        try {
            ArrayList<String> taskDetail = new ArrayList<>();
            if (Files.exists(Paths.get(fileName))) {
                System.out.println("Reading Tasks from file");
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    taskDetail.add(scanner.nextLine());
                }
            } else {

            }
            return taskDetail;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error reading Tasks from the storage");
        }
    }
}

package starkchatbot.storage;

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
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    taskDetail.add(scanner.nextLine());
                }
                scanner.close();
                System.out.println("Tasks read from the file completed");
            }else{
                System.out.println("File does not exist");
            }
            return taskDetail;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error reading file");
        }
    }
}

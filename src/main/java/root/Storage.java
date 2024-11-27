package root;

import tasks.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }
    
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    
        while ((line = reader.readLine()) != null) {
            try {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2], parts[1].equals("1")));
                        break;
                    case "D":
                        LocalDateTime deadline = LocalDateTime.parse(parts[3], formatter);
                        tasks.add(new Deadline(parts[2], deadline, parts[1].equals("1")));
                        break;
                    case "E":
                        LocalDateTime start = LocalDateTime.parse(parts[3], formatter);
                        LocalDateTime end = LocalDateTime.parse(parts[4], formatter);
                        tasks.add(new Event(parts[2], start, end, parts[1].equals("1")));
                        break;
                    default:
                        System.out.println("Skipping invalid task type: " + line);
                }
            } catch (Exception e) {
                System.out.println("Skipping invalid task entry: " + line);
            }
        }
        reader.close();
        return tasks;
    }    

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
        }
    }
}

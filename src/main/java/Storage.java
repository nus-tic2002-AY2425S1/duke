import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE = "duke.txt";
    private static final Path DATA_PATH = Paths.get(DATA_DIRECTORY, DATA_FILE);

    public static void saveTasks(List<Task> tasks) throws IOException {
        createDataDirectoryIfNotExists();
        try (BufferedWriter writer = Files.newBufferedWriter(DATA_PATH)) {
            for (Task task : tasks) {
                writer.write(taskToFileString(task));
                writer.newLine();
            }
        }
    }

    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(DATA_PATH)) {
            try (BufferedReader reader = Files.newBufferedReader(DATA_PATH)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = fileStringToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        }
        return tasks;
    }

    private static void createDataDirectoryIfNotExists() throws IOException {
        Files.createDirectories(Paths.get(DATA_DIRECTORY));
    }

    private static String taskToFileString(Task task) {
        String typeCode;
        String additionalInfo = "";

        if (task instanceof ToDo) {
            typeCode = "T";
        } else if (task instanceof Deadline) {
            typeCode = "D";
            additionalInfo = " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            typeCode = "E";
            additionalInfo = " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }

        return String.format("%s | %d | %s%s", typeCode, task.isDone() ? 1 : 0, task.getDescription(), additionalInfo);
    }

    private static Task fileStringToTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Warning: Skipping invalid line in data file: " + line);
            return null;
        }

        String typeCode = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (typeCode) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("Warning: Skipping invalid Deadline task in data file: " + line);
                    return null;
                }
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("Warning: Skipping invalid Event task in data file: " + line);
                    return null;
                }
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                System.out.println("Warning: Unknown task type in data file: " + line);
                return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}

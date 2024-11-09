import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // Load tasks from file
    public ArrayList<Task> load() throws SnitchException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new SnitchException("Error creating storage file: " + e.getMessage());
            }
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        throw new SnitchException("Corrupted data in file.");
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new SnitchException("Error reading storage file: " + e.getMessage());
        }
        return tasks;
    }

    // Save tasks to file
    public void save(ArrayList<Task> tasks) throws SnitchException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SnitchException("Error writing to storage file: " + e.getMessage());
        }
    }

    // Convert task to file format
    private String taskToFileFormat(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("T | ").append(task.isDone ? "1" : "0").append(" | ").append(task.description);
        } else if (task instanceof Deadline) {
            sb.append("D | ").append(task.isDone ? "1" : "0").append(" | ")
                    .append(task.description).append(" | ").append(((Deadline) task).by);
        } else if (task instanceof Event) {
            sb.append("E | ").append(task.isDone ? "1" : "0").append(" | ")
                    .append(task.description).append(" | ").append(((Event) task).from)
                    .append(" | ").append(((Event) task).to);
        }
        return sb.toString();
    }
}
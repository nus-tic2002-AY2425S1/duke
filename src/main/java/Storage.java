import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> load() throws SnitchException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new SnitchException("Error creating storage file: " + e.getMessage());
            }
            return tasks;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T": task = new Todo(parts[2]); break;
                    case "D": task = new Deadline(parts[2], parts[3]); break;
                    case "E": task = new Event(parts[2], parts[3], parts[4]); break;
                    default: throw new SnitchException("Corrupted data in file.");
                }
                if (parts[1].equals("1")) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new SnitchException("Error reading storage file: " + e.getMessage());
        }
        return tasks;
    }

    public void save(TaskList tasks) throws SnitchException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SnitchException("Error writing to storage file: " + e.getMessage());
        }
    }

    private String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by;
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to;
        }
        return "";
    }
}
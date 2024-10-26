package denny.storage;

import denny.task.Deadline;
import denny.task.Event;
import denny.task.Task;
import denny.task.ToDo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles persistence of tasks to and from file storage.
 * Supports saving and loading of different task types with their associated data.
 */
public class Storage {
    private final String filePath;
    private static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Creates a new Storage instance with the specified file path.
     * @param filePath Path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDirectoryIfNotExists();
    }

    /**
     * Creates the storage directory if it doesn't exist.
     */
    private void createDirectoryIfNotExists() {
        File directory = new File(filePath).getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Loads tasks from the storage file.
     * @return List of Task objects read from storage
     * @throws IOException if there are errors reading the file
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (IllegalArgumentException e) {
                    System.err.println("Error parsing line: " + line + ". " + e.getMessage());
                }
            }
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to storage.
     * @param tasks List of Task objects to save
     * @throws IOException if there are errors writing to the file
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(formatTask(task));
                writer.newLine();
            }
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        try {
            switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    String by = parts[3].trim();
                    task = new Deadline(description, convertStorageToInputFormat(by));
                    break;
                case "E":
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    task = new Event(description,
                            convertStorageToInputFormat(from),
                            convertStorageToInputFormat(to));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type: " + type);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing date/time: " + e.getMessage());
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    private String convertStorageToInputFormat(String storageDateTime) {
        LocalDateTime dateTime = LocalDateTime.parse(storageDateTime, STORAGE_FORMATTER);
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    private String formatTask(Task task) {
        String type = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String isDone = task.isDone() ? "1" : "0";
        StringBuilder formatted = new StringBuilder(String.format("%s | %s | %s",
                type, isDone, task.getDescription()));

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            formatted.append(" | ").append(deadline.getBy().format(STORAGE_FORMATTER));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            formatted.append(" | ")
                    .append(event.getFrom().format(STORAGE_FORMATTER))
                    .append(" | ")
                    .append(event.getTo().format(STORAGE_FORMATTER));
        }
        return formatted.toString();
    }
}

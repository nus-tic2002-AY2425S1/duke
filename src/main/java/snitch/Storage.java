package snitch;

import snitch.task.Task;
import snitch.task.Todo;
import snitch.task.Deadline;
import snitch.task.Event;
import snitch.SnitchException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles saving and loading tasks to/from a file.
 * Provides methods to save tasks and retrieve them on startup.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage instance for tasks in a specified file.
     *
     * @param filePath The relative path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the file.
     * If the file does not exist, it will be created.
     *
     * @return A list of tasks loaded from the file.
     * @throws SnitchException If an error occurs while loading tasks.
     */
    public ArrayList<Task> load() throws SnitchException {
        assert filePath != null : "File path must not be null";

        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            createFile(); // Create file if it doesn't exist
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new SnitchException("Failed to load tasks from file.");
        }

        assert tasks != null : "Loaded tasks must not be null";
        return tasks;
    }

    /**
     * Saves the current tasks to the file.
     *
     * @param tasks A list of tasks to be saved.
     * @throws SnitchException If an error occurs while saving tasks.
     */
    public void save(ArrayList<Task> tasks) throws SnitchException {
        assert tasks != null : "Tasks to save must not be null";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : tasks) {
                assert task != null : "Task to save must not be null";
                writer.write(taskToFileString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SnitchException("Failed to save tasks to file.");
        }
    }

    /**
     * Creates the file and necessary directories if they do not exist.
     *
     * @throws SnitchException If an error occurs while creating the file.
     */
    private void createFile() throws SnitchException {
        assert filePath != null : "File path must not be null";

        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new SnitchException("Failed to create file.");
        }
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line The line representing a task in the file.
     * @return The parsed Task object.
     * @throws SnitchException If the line format is unrecognized.
     */
    private Task parseTask(String line) throws SnitchException {
        assert line != null && !line.isEmpty() : "Line to parse must not be null or empty";

        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "Line must have at least 3 parts";

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadlineDate = LocalDateTime.parse(parts[3], formatter);
                Deadline deadline = new Deadline(description, deadlineDate.format(formatter));
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new SnitchException("Unrecognized task format: " + line);
        }
    }

    /**
     * Converts a Task object into a formatted string for saving in the file.
     *
     * @param task The task to be converted.
     * @return The formatted string representation of the task.
     */
    private String taskToFileString(Task task) {
        assert task != null : "Task to serialize must not be null";

        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
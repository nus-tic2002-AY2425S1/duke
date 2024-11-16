package duke.dancepop.utils;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Storage {
    private static final String FILE_PATH = "volume/";
    private static final String DEFAULT_FILE_NAME = "data.csv";
    private static Optional<String> customFileName = Optional.empty();

    /**
     * Save all Task objects to a CSV file.
     */
    public static void saveToFile(String fileName) {
        Path filePath = Paths.get(FILE_PATH, fileName);
        Log.printMsg("Saving tasks to " + filePath);

        if (!fileName.equalsIgnoreCase(DEFAULT_FILE_NAME)) {
            customFileName = Optional.of(fileName);
        }

        try {
            Files.deleteIfExists(filePath);

            try (FileWriter writer = new FileWriter(filePath.toString())) {
                if (TaskList.getTasks().isEmpty()) {
                    createEmptyCsvFile(writer);
                } else {
                    writeTasksToFile(writer, TaskList.getTasks());
                }
            }
        } catch (IOException ioe) {
            Log.printMsg("Error occurred while saving file: ", ioe.getMessage());
        }
    }

    public static void saveToFile() {
        saveToFile(customFileName.orElse(DEFAULT_FILE_NAME));
    }

    /**
     * Load Task objects from a CSV file into TaskList.
     */
    public static void loadFile(String fileName) {
        Path filePath = Paths.get(FILE_PATH, fileName);
        Log.printMsg("Loading data from " + filePath);

        if (!Files.exists(filePath)) {
            Log.printMsg("No saved data found.");
            return;
        }

        TaskList.clear();

        if (!fileName.equalsIgnoreCase(DEFAULT_FILE_NAME)) {
            customFileName = Optional.of(fileName);
        }

        try {
            List<String> lines = Files.readAllLines(filePath).stream()
                    .filter(line -> !line.trim().isEmpty())
                    .toList();

            lines.forEach(Storage::parseAndAddTask);
        } catch (IOException ioe) {
            Log.printMsg("Error occurred while loading file: ", ioe.getMessage());
        }
    }

    public static void loadFile() {
        loadFile(customFileName.orElse(DEFAULT_FILE_NAME));
    }

    private static void writeTasksToFile(FileWriter writer, List<Task> tasks) throws IOException {
        for (Task task : tasks) {
            writer.append(serializeTask(task)).append("\n");
        }
        writer.flush();
    }

    private static String serializeTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task instanceof Todo ? "T" :
                        task instanceof Deadline ? "D" : "E")
                .append("|").append(task.getDone())
                .append("|").append(task.getDescription());

        if (task instanceof Deadline deadline) {
            sb.append("|").append(deadline.getDeadline());
        } else if (task instanceof Event event) {
            sb.append("|").append(DateTimeUtil.toIsoString(event.getStart()))
                    .append("|").append(DateTimeUtil.toIsoString(event.getEnd()));
        }

        return sb.toString();
    }

    private static void parseAndAddTask(String line) {
        String[] parts = line.split("\\|");
        Task task;

        switch (parts[0]) {
            case "T" -> task = new Todo(parts[2]);
            case "D" -> task = new Deadline(parts[2], DateTimeUtil.isoToLocalDateTime(parts[3]));
            case "E" ->
                    task = new Event(parts[2], DateTimeUtil.isoToLocalDateTime(parts[3]), DateTimeUtil.isoToLocalDateTime(parts[4]));
            default -> throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }

        task.setDone(parts[1]);
        TaskList.add(task);
    }

    private static void createEmptyCsvFile(FileWriter writer) throws IOException {
        writer.append("").flush();
    }
}

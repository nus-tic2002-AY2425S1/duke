package duke.dancepop.utils;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;
import duke.dancepop.enums.TaskEnum;
import duke.dancepop.exceptions.ExceptionConsts;
import duke.dancepop.exceptions.FileException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Storage {
    private static final String FILE_PATH = "volume/";
    private static final String DEFAULT_FILE_NAME = "data.csv";
    private static Optional<String> customFileName = Optional.empty();

    /**
     * Saves all Task objects to a CSV file with the specified file name.
     * If the specified file exists, it will be overwritten.
     * If file doesn't exist, it will be created.
     *
     * @param fileName The name of the file to save the tasks.
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

    /**
     * Saves all Task objects to a CSV file.
     * If a custom file name has been specified, it will use that; otherwise, it uses the default file name.
     */
    public static void saveToFile() {
        saveToFile(customFileName.orElse(DEFAULT_FILE_NAME));
    }

    /**
     * Loads Task objects from a CSV file with the specified file name into TaskList.
     * If the file does not exist, a log message is printed, and no tasks are loaded.
     *
     * @param fileName The name of the file to load tasks from.
     */
    public static void loadFromFile(String fileName) {
        assert fileName != null && !fileName.isBlank() : "File name must not be null or blank";
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

            List<Task> fileTasks = new ArrayList<>();
            for (String line : lines) {
                Task fileTask = parseAndAddTask(line);
                fileTasks.add(fileTask);
            }
            TaskList.addAll(fileTasks);
        } catch (IOException | FileException ioe) {
            Log.printMsg(ioe.getMessage());
        }
    }

    /**
     * Loads Task objects from a CSV file.
     * If a custom file name has been specified, it will use that.
     * Otherwise, it uses the default file name - data.csv
     */
    public static void loadFromFile() {
        loadFromFile(customFileName.orElse(DEFAULT_FILE_NAME));
    }

    private static void writeTasksToFile(FileWriter writer, List<Task> tasks) throws IOException {
        for (Task task : tasks) {
            writer.append(serializeTask(task)).append("\n");
        }
        writer.flush();
    }

    private static String serializeTask(Task task) {
        assert task != null : "Task to serialize must not be null";
        StringBuilder sb = new StringBuilder();
        sb.append(task instanceof Todo ? "T" :
                        task instanceof Deadline ? "D" : "E")
                .append("|").append(task.getIsDone())
                .append("|").append(task.getDescription());

        if (task instanceof Deadline deadline) {
            sb.append("|").append(deadline.getDeadline());
        } else if (task instanceof Event event) {
            sb.append("|").append(DateTimeUtil.toIsoString(event.getStart()))
                    .append("|").append(DateTimeUtil.toIsoString(event.getEnd()));
        }

        return sb.toString();
    }

    private static Task parseAndAddTask(String line) throws FileException {
        String[] parts = line.split("\\|");

        if (parts.length < 3) {
            throw new FileException(ExceptionConsts.INVALID_CSV_FILE_FORMAT_ERROR);
        }

        Task task;
        switch (parts[0]) {
            case "T" -> {
                if (parts.length != 3) {
                    throw new FileException(MessageFormat.format(ExceptionConsts.INVALID_TASK_CSV_FORMAT_ERROR, TaskEnum.TODO));
                }
                task = new Todo(parts[2]);
            }
            case "D" -> {
                if (parts.length != 4) {
                    throw new FileException(MessageFormat.format(ExceptionConsts.INVALID_TASK_CSV_FORMAT_ERROR, TaskEnum.DEADLINE));
                }
                task = new Deadline(parts[2], DateTimeUtil.isoToLocalDateTime(parts[3]));
            }
            case "E" -> {
                if (parts.length != 5) {
                    throw new FileException(MessageFormat.format(ExceptionConsts.INVALID_TASK_CSV_FORMAT_ERROR, TaskEnum.EVENT));
                }
                task = new Event(parts[2], DateTimeUtil.isoToLocalDateTime(parts[3]), DateTimeUtil.isoToLocalDateTime(parts[4]));
            }
            default ->
                    throw new FileException(MessageFormat.format(ExceptionConsts.UNKNOWN_TASK_CSV_FORMAT_ERROR, parts[0]));
        }

        task.setIsDone(parts[1]);
        return task;
    }

    private static void createEmptyCsvFile(FileWriter writer) throws IOException {
        writer.append("").flush();
    }
}

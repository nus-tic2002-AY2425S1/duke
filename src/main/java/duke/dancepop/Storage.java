package duke.dancepop;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;
import duke.dancepop.utils.DateTimeUtil;
import duke.dancepop.utils.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    // TODO: Refactor all of these if needed, so messy.
    private static final String FILE_PATH = "volume/data.csv";

    public static void saveToFile() {
        Path filePath = Paths.get(FILE_PATH);
        try {
            // Delete CSV file if exists
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }

            // Write to new CSV file
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                // Create empty file if no task list
                if (TaskList.getTasks().isEmpty()) {
                    createEmptyCsvFile(writer);
                    return;
                }
                // TODO: Re-evaluate if csv should really have empty pipes | to fit a general header
                // Maybe do a type, (Un)Mark, metadata header
                // Then content wise just do this: type | (Un)Mark | description - /by or /from - /to
                // And split via hyphen or some other string that isn't commonly used
                for (Task task : TaskList.getTasks()) {
                    if (task instanceof Todo todo) {
                        writer.append("T|").append(String.valueOf(todo.getDone())).append("|").append(todo.getDescription()).append("\n");
                    } else if (task instanceof Deadline deadline) {
                        writer.append("D|").append(String.valueOf(deadline.getDone())).append("|").append(deadline.getDescription()).append("|").append(deadline.getDeadline().toString()).append("\n");
                    } else if (task instanceof Event event) {
                        writer.append("E|").append(String.valueOf(event.getDone())).append("|").append(event.getDescription()).append("|").append(DateTimeUtil.toCsvString(event.getStart())).append("|").append(DateTimeUtil.toCsvString(event.getEnd())).append("\n");
                    }
                }

                writer.flush();
            }
        } catch (IOException ioe) {
            Log.printMsg("Error occurred while saving file: ", ioe.getMessage());
        }
    }

    public static void loadFile() {
        Log.printMsg("Loading data from " + FILE_PATH);
        Path filePath = Paths.get(FILE_PATH);
        if (!Files.exists(filePath)) {
            Log.printMsg("No saved data found.");
            return;
        }

        try {
            // Ignore empty and whitespace lines
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH))
                    .stream()
                    .filter(line -> !line.trim().isEmpty())
                    .toList();

            for (String line : lines) {
                // type | (Un)Mark | description | /by or /from | /to
                String[] parts = line.split("\\|");
                switch (parts[0]) {
                    case "T":
                        Task todo = new Todo(parts[2]);
                        todo.setDone(parts[1]);
                        TaskList.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(parts[2], DateTimeUtil.isoToLocalDateTime(parts[3]));
                        deadline.setDone(parts[1]);
                        TaskList.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(parts[2], DateTimeUtil.isoToLocalDateTime(parts[3]), DateTimeUtil.isoToLocalDateTime(parts[4]));
                        event.setDone(parts[1]);
                        TaskList.add(event);
                        break;
                }
            }
        } catch (IOException ioe) {
            Log.printMsg("Error occurred while loading file: ", ioe.getMessage());
        }
    }

    private static void createEmptyCsvFile(FileWriter writer) throws IOException {
        writer.append("");
        writer.flush();
    }
}

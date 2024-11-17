package storage;

import tasks.Deadline;
import tasks.Task;
import tasks.ToDo;
import tasks.Event;
import tasks.FixedDuration;
import exception.DukeException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static parser.DateTimeParser.parseDateTime;

/**
 * Handles loading and saving of tasks to and from a file
 * Solution below adapted from https://www.baeldung.com/java-read-write-files-different-threads
 */
public class FileProcessor {
    String fileLocation;

    /**
     * Constructor for a file processor with a specified file location
     * @param fileLocation
     */
    public FileProcessor(String fileLocation){
        this.fileLocation = fileLocation;
    }

    /**
     * Loads file from the file location
     * @return List of tasks loaded from file
     * @throws DukeException If an error occur while loading file
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(fileLocation);
        if(!file.exists()) return tasks;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                String [] taskParts = line.split("\\|");
                Task task;
                switch (taskParts[0]){
                    case "T":
                        assert taskParts.length == 4 : "Todo task must have 3 parts";
                        task = new ToDo(taskParts[2]);
                        break;
                    case "D":
                        assert taskParts.length == 4 : "Deadline task must have 4 parts";
                        task =  new Deadline(taskParts[2], parseDateTime(taskParts[3]));
                        break;
                    case "E":
                        assert taskParts.length == 5 : "Event task must have 5 parts";
                        task = new Event(taskParts[2], parseDateTime(taskParts[3]), parseDateTime(taskParts[4]));
                        break;
                    case "FD":
                        assert taskParts.length == 4 : "FixedDuration task must have 4 parts";
                        task = new FixedDuration(taskParts[2], Integer.parseInt(taskParts[3]));
                        break;
                    default:
                        throw new DukeException("Invalid task type in file");
                }
                if(Objects.equals(taskParts[1], "1")) task.markDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new DukeException("Error loading file" + e.getMessage());
        }
        return tasks;
    }

    /**
     * Save tasks to file
     * @param tasks The list of tasks to be saved
     * @throws DukeException If an error occur while saving file
     */
    public void save(List<Task> tasks) throws DukeException{
        File file = new File(fileLocation);
        file.getParentFile().mkdir();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            for(Task task : tasks) {
                bufferedWriter.write(task.toFileFormat());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DukeException("Error saving to file: " + e);
        }

    }

}

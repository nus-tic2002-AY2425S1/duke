package storage;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import exception.DukeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileProcessor {
    String fileLocation;

    public FileProcessor(String fileLocation){
        this.fileLocation = fileLocation;
    }

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
                        task = new ToDo(taskParts[2]);
                        break;
                    case "D":
                        task =  new Deadline(taskParts[2], taskParts[3]);
                        break;
                    case "E":
                        task = new Event(taskParts[2], taskParts[3], taskParts[4]);
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

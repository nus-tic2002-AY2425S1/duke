package alice.storage;

import alice.task.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1>Storage</h1>
 * The Storage class is responsible for
 * retrieving any existing data, create
 * new txt file or saves existing data
 * into the txt file.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class Storage {
    private File f;
    public static final String DEFAULT_NAME = "tasks.txt";
    private static final String[] DATE_PATTERNS = {
            "yyyy-MM-dd",
            "yyyy-M-d",
            "dd-MM-yyyy",
            "d-M-yyyy",
            "MM/dd/yyyy",
            "M/d/yyyy",
            "yyyy/MM/dd",
            "yyyy/M/d",
            "yyyy-MM-dd",
            "yyyy-M-d",
            "dd MMM yyyy",
            "d MMM yyyy",
            "dd/MM/yyyy",
            "d/M/yyyy",
            "dd-MM-yyyy",
            "d-M-yyyy",
            "MMM dd yyyy",
            "MMM d yyyy"
    };

    private static DateTimeFormatter buildFormatter() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        for (String pattern : DATE_PATTERNS) {
            builder.parseCaseInsensitive()
                    .appendOptional(DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)).optionalStart()
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .optionalEnd();
        }
        return builder.toFormatter().withResolverStyle(ResolverStyle.SMART);
    }

    public Storage(){
        this(DEFAULT_NAME);
    }
    public Storage(String filePath){
        f = new File(filePath);

    }

    public ArrayList<Task> load() throws IOException {
        if(f.createNewFile()){
            System.out.println("new file created");
            return new ArrayList<>();
        }
        ArrayList<Task> loadList = new ArrayList<>();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while(s.hasNext()) {
            String line = s.nextLine();
            String[] instruction = line.split(" ");
            StringBuilder body = new StringBuilder();
            StringBuilder param = new StringBuilder();
            StringBuilder param2 = new StringBuilder();
            int i = 3;
            switch (instruction[0]) {
                case "T":
                    if (instruction.length == 1){
                        System.out.println("The description of todo cannot be empty!");
                        break;
                    }
                    body.append(instruction[2]);
                    while (i < instruction.length) {
                        body.append(" ").append(instruction[i]);
                        i++;
                    }
                    loadList.add(new Todo(body.toString(), Boolean.parseBoolean(instruction[1])));
                    break;
                case "D":
                    if (instruction.length == 1) {
                        System.out.println("The description of deadline cannot be empty!");
                        break;
                    }
                    body.append(instruction[2]);
                    try {
                        while (!instruction[i].equals("/by")) {
                            body.append(" ").append(instruction[i]);
                            i++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Do include the /by flag!");
                        break;
                    }
                    i++;
                    try {
                        param.append(instruction[i]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Do include the day after /by!");
                        break;
                    }

                    i++;
                    while (i < instruction.length) {
                        param.append(" ").append(instruction[i]);
                        i++;
                    }
                    try {
                        loadList.add(new Deadline(body.toString(), Boolean.parseBoolean(instruction[1]), LocalDate.parse(param, buildFormatter())));
                    } catch (DateTimeParseException e){
                        System.out.println("Wrong datetime input!");
                    }
                    break;
                case "E":
                    body.append(instruction[2]);
                    try {
                        while (!instruction[i].equals("/from")) {
                            body.append(" ").append(instruction[i]);
                            i++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Do include the /from flag!");
                        break;
                    }
                    i++;
                    try {
                        param.append(instruction[i]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Do include the day after /from!");
                        break;
                    }
                    i++;
                    try {
                        while (!instruction[i].equals("/to")) {
                            param.append(" ").append(instruction[i]);
                            i++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Do include the /to flag!");
                        break;
                    }
                    i++;
                    try {
                        param2.append(instruction[i]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Do include the day after /to!");
                        break;
                    }
                    i++;
                    while (i < instruction.length) {
                        param2.append(" ").append(instruction[i]);
                        i++;
                    }
                    loadList.add(new Event(body.toString(), Boolean.parseBoolean(instruction[1]), param.toString(), param2.toString()));
                    break;
                default:
                    System.out.println("Incorrect instruction format!");
            }

        }

        return loadList;
    }

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }




}

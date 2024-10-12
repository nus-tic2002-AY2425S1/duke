import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File f;
    public static final String defaultName = "data/duke.txt";

    public Storage(){
        this(defaultName);
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
                    System.out.println(body.toString() + instruction[1]);
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
                    loadList.add(new Deadline(body.toString(), Boolean.parseBoolean(instruction[1]), param.toString()));

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
                    System.out.println("Please put an instruction I can understand :(");
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

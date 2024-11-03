package Chad.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Chad.Exception.ChadException;
import java.io.FileNotFoundException; 

public class Storage {
    private File file;

    // Constructor to initialize the Storage class with the specified path
    public Storage(String path) throws ChadException {
        this.file = new File(path);
        initializeFile(); // Ensure the file and parent directory are set up
    }

    // Initializes the file and its parent directory
    private void initializeFile() throws ChadException {
        File parentDirectory = file.getParentFile();

        // Create the parent directory if it does not exist
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdirs()) {
                throw new ChadException("Failed to create directory: " + parentDirectory.getPath());
            }
        }

        // Create the file if it does not exist
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new ChadException("Failed to create file: " + file.getPath());
                }
            } catch (IOException e) {
                throw new ChadException("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    // Reads the content of the file and returns it as a string
    public String read() throws ChadException {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new ChadException("File not found: " + file.getPath());
        } catch (IOException e) {
            throw new ChadException("An error occurred while reading the file: " + e.getMessage());
        }
        return content.toString().trim(); // Return the file content
    }

    // Saves the given content to the file
    public void save(String content) throws ChadException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content); // Write content to the file
        } catch (IOException e) {
            throw new ChadException("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

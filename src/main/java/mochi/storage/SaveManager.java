package mochi.storage;

import mochi.common.exception.ExceptionMessages;
import mochi.ui.Ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The SaveManager class handles data loading, saving, and backup management for chat data.
 * It provides methods to load data from a file, save data to a file, verify directory validity,
 * and back up existing files with a specified postfix.
 */
public class SaveManager {
    final static String POSTFIX = ".bk";
    final static String BACKUP_FILE = "data/MochiSaves.txt";

    final Path loadFile;

    public SaveManager() {
        this(BACKUP_FILE);
    }

    public SaveManager(String filePath) {
        loadFile = Paths.get(filePath);
        checkValidDirectory(filePath);
    }

    /**
     * Loads tasks from the specified file as an ArrayList of strings.
     *
     * @return an ArrayList of strings containing the data from the file, or null if the file does not exist.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public ArrayList<String> load() throws IOException {
        if (!Files.exists(loadFile)) {
            return null;
        } else {
            List<String> tmp = Files.readAllLines(loadFile, StandardCharsets.UTF_8);
            return new ArrayList<>(tmp);
        }
    }

    /**
     * Saves an ArrayList of tasks to the default backup file path. If the file exists, it creates a backup.
     *
     * @param input the ArrayList of strings to save.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<String> input) throws IOException {
        Path saveFile = Paths.get(BACKUP_FILE);
        if (Files.exists(saveFile)) {
            backUpFileWithPrefix();
        }
        Files.write(saveFile,
                input,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    private void checkValidDirectory(String pathString) {
        try {
            Paths.get(pathString);
        } catch (InvalidPathException e) {
            Ui.response(ExceptionMessages.INVALID_PATH_EXCEPTION);
        } catch (SecurityException e) {
            Ui.response(ExceptionMessages.SECURITY_PATH_EXCEPTION);
        }
    }

    private void backUpFileWithPrefix() throws IOException {
        Path sourceFile = Paths.get(BACKUP_FILE);
        Path targetFile = Paths.get(BACKUP_FILE + POSTFIX);
        Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deleteData() throws IOException {
        if (Files.exists(loadFile)) {
            Files.delete(loadFile);
        }
    }
}

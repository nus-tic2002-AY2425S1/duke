package mochi.storage;

import mochi.common.exception.ExceptionMessages;
import mochi.ui.Ui;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
  final static String POSTFIX = ".bk";
  final static String BACKUP_FILE = "data/MochiSaves.txt";

  final Path loadFile;
  public SaveManager() {
    this(BACKUP_FILE);
  }
  public SaveManager(String filePath) {
    loadFile = Paths.get(filePath);
    isValidDirectory(filePath);
  }
  public ArrayList<String> load() throws IOException {
    if (!Files.exists(loadFile)) {
      return null;
    } else {
      List<String> tmp = Files.readAllLines(loadFile, StandardCharsets.UTF_8);
      return new ArrayList<>(tmp);
    }
  }
  public void save(ArrayList<String> input) throws IOException {
    Path saveFile = Paths.get(BACKUP_FILE);
    if(Files.exists(saveFile)) {
      backUpFileWithPrefix(BACKUP_FILE,POSTFIX);
    }
    Files.write(saveFile, input, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }
  public boolean isValidDirectory(String pathString) {
    try {
      Path path = Paths.get(pathString);
      return Files.exists(path) && Files.isDirectory(path);
    } catch (InvalidPathException e) {
      Ui.response(ExceptionMessages.INVALID_PATH_EXCEPTION);
    } catch (SecurityException e) {
      Ui.response(ExceptionMessages.SECURITY_PATH_EXCEPTION);
    }
    return false;
  }
  public void backUpFileWithPrefix(String file,String postfix) throws IOException {
    Path sourceFile = Paths.get(file);
    Path targetFile = Paths.get(file + postfix);
    Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
  }
}

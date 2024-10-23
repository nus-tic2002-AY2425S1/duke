import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
  final static String BACKUP_FILE = "data/MochiSaves.txt";
  final static String POSTFIX = ".bk";
  public static ArrayList<String> load() throws IOException {
    Path loadFile = Paths.get(BACKUP_FILE);
    if (!Files.exists(loadFile)) {
      return null;
    } else {
      List<String> tmp = Files.readAllLines(loadFile, StandardCharsets.UTF_8);
      return new ArrayList<>(tmp);
    }
  }
  public static void save(ArrayList<String> input) throws IOException {
    Path saveFile = Paths.get(BACKUP_FILE);
    if(Files.exists(saveFile)) {
      backUpFileWithPrefix(BACKUP_FILE,POSTFIX);
    }
    Files.write(saveFile, input, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }

  public static void backUpFileWithPrefix(String file,String postfix) throws IOException {
    Path sourceFile = Paths.get(file);
    Path targetFile = Paths.get(file + postfix);
    Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
  }
}

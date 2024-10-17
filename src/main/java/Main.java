import checkbot.Parser.Parser;
import checkbot.Storage.StorageFile;
import checkbot.Ui.TextUi;

public class Main {
    public static void main(String[] args) {
        TextUi.printHello();
        StorageFile.readFile();
        Parser.parse();
    }
}

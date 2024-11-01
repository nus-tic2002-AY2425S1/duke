public class StorageFileException extends JavaroException {
    
    public StorageFileException(String error) {
        super(error);
    }

    public StorageFileException(String error, String info) {
        super(error, info);
    }
    
    public StorageFileException(String error, String info, String usage) {
        super(error, info, usage);
    }
}

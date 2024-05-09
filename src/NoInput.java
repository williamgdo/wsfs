import com.williamgdo.wtfs.FileSystem;
import com.williamgdo.wtfs.FileSystemFacade;

public class NoInput {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        FileSystemFacade fileSystem = new FileSystemFacade(fs);

        fileSystem.uploadFileToFileSystem("files/examples/text", "text");

        fs.printReport();

        fileSystem.downloadFileFromFileSystem("text", "files/downloads/text2");
    }

}

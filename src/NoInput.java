import com.williamgdo.wtfs.FileSystem;
import com.williamgdo.wtfs.Inode;

import java.util.Arrays;

public class NoInput {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        fileSystem.uploadFileToFileSystem("example-files/text", "text");

        fileSystem.report();

        fileSystem.downloadFileFromFileSystem("text", "text2");
    }

}

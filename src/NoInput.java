import com.williamgdo.wtfs.FileSystem;
import com.williamgdo.wtfs.Inode;

import java.util.Arrays;

public class NoInput {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        fileSystem.uploadFileToFileSystem("files/examples/text", "text");

        fileSystem.printSuperblockReport();



//        fileSystem.downloadFileFromFileSystem("text", "text2");
    }

}

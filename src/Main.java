import java.util.Scanner;
import com.williamgdo.wtfs.CommandLineInterface;

public class Main {
    public static void main(String[] args) {
        CommandLineInterface cli = new CommandLineInterface();

        cli.printHelp();

        while (true) {
            String nextFunctionToExecute = cli.createOrMountFsMenu();

            if (nextFunctionToExecute.equalsIgnoreCase("mount"))
                cli.mountFsMenu();

            if (nextFunctionToExecute.equalsIgnoreCase("create")) {
                cli.createFsMenu();
            }

            if (nextFunctionToExecute.equalsIgnoreCase("exit")) {
                cli.exitCli();
                break;
            }
        }
    }


}

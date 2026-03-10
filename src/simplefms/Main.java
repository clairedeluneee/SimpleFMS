package simplefms;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("meow");

        s.useDelimiter(System.lineSeparator());

        ManagementSystem ms = new ManagementSystem();

        // Denotes what number the user put in.
        int action = -1;


        while (true) {
            // Break check.
            if (!ms.isActive()) break;

            // Clear screen.
            clear();

            // Show prompt.
            prints("""
                    ============ FILE MANAGEMENT SYSTEM ==========
                    1. Create a Text File and Write Content
                    2. Convert File Path to Absolute Path
                    3. Display File Path Information (Name Count)
                    4. Check File Read and Execute Permission
                    5. Display File Attributes
                    6. Read a Text File
                    7. Exit
                    Enter your choice:
                    """);
            try {

                // Ask for input
                action = s.nextInt();
                
            } catch (Exception e) {
                // Input likely invalid, refreshing.
                continue;
            }

            // i love reusing code
            // Selection based on user input.
            // Out of bounds check included.
            switch (action) {
                // 1. Create a Text File and Write Content
                case 1:
                    ms.doUserWrite();
                    break;
                
                // 2. Convert File Path to Absolute Path
                case 2:
                    prints("Filename: ");
                    String fileToGetAbsolutePathFrom = safeInput();

                    try {
                        print(ms.getAbsolutePathFromRelativeFile(fileToGetAbsolutePathFrom));
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;
                
                // 3. Display File Path Information (Name Count)
                case 3:
                    prints("Filename: ");
                    String fileToGetPathInformationFrom = safeInput();

                    try {
                        ms.printFilePathInformation(Paths.get(fileToGetPathInformationFrom));
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;

                // 4. Check File Read and Execute Permission
                case 4:
                    prints("Filename: ");
                    String fileToGetPermissionsFrom = safeInput();

                    try {
                        ms.displayFilePermissions(fileToGetPermissionsFrom);
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;
                
                // 5. Display File Attributes
                case 5:
                    prints("Filename: ");
                    String fileToGetAttributesFrom = safeInput();

                    try {
                        ms.displayFileAttributes(Paths.get(fileToGetAttributesFrom));
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;
                
                // 6. Read a Text File
                case 6:
                    ms.doUserRead();
                    break;
                
                // 7. Exit
                case 7:
                    ms.exit();

                // If selection is not within range, refresh.
                default:
                    continue;
            }

            

            // Prompt user to press enter to continue.
            print("\nPress enter to continue");
            @SuppressWarnings("unused")
            String thisIsHereSoThatTheUserHasToPressEnterToRefreshTheThing = s.next();
        }

        s.close();
    } 

    public static void clear() {
        System.out.print("\u001b[2J");
        System.out.flush();
    }
    private static void print(String message) {
        System.out.println(message + "");
    }
    private static void prints(String message) {
        System.out.print(message + "");
    }
    /**
     * Asks the user for input. Catches invalid inputs and returns an empty string if it happens.
     * @return
     */
    public static String safeInput() {

        String output = "";

        try {
            // Get the next line
            output = s.next();
        
        } catch (Exception e) {
        }

        return output;
    }
}

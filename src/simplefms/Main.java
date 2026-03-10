package simplefms;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("meow");

        ManagementSystem ms = new ManagementSystem();

        int action = -1;

        Scanner s = new Scanner(System.in);
        while (true) {
            clear();

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

                action = s.nextInt();
                
            } catch (Exception e) {
                continue;
            }

            if (action < 1) continue;
            if (action > 7) continue;

            // i love reusing code
            switch (action) {
                case 1:
                    ms.doUserWrite();
                    break;
                
                case 2:
                    prints("Filename: ");
                    String fileToGetAbsolutePathFrom = s.nextLine();

                    try {
                        print(ms.getAbsolutePathFromRelativeFile(fileToGetAbsolutePathFrom));
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;
                
                case 3:
                    prints("Filename: ");
                    String fileToGetPathInformationFrom = s.nextLine();

                    try {
                        ms.displayFileAttributes(Paths.get(fileToGetPathInformationFrom));
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;

                case 4:
                    prints("Filename: ");
                    String fileToGetPermissionsFrom = s.nextLine();

                    try {
                        ms.displayFilePermissions(fileToGetPermissionsFrom);
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;
                
                case 5:
                    prints("Filename: ");
                    String fileToGetAttributesFrom = s.nextLine();

                    try {
                        ms.displayFileAttributes(Paths.get(fileToGetAttributesFrom));
                    } catch (Exception e) {
                        print("file not found");
                    }
                    break;
                
                case 6:
                    ms.doUserRead();
                    break;

                case 7:
                    ms.exit();

                default:
                    continue;
            }


            if (!ms.isActive()) break;

            print("\nPress enter to continue");
            @SuppressWarnings("unused")
            String thisIsHereSoThatTheUserHasToPressEnterToRefreshTheThing = s.nextLine();
        }
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
}

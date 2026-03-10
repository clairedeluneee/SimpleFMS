package simplefms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.AccessMode.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * The management system itself. 
 */
public class ManagementSystem {
    private FileSystem fs;

    private boolean loopActive = true;
    public boolean isActive() { return this.loopActive;}
    

    // Constructors
    public ManagementSystem() {
        fs = FileSystems.getDefault();
    }

    public ManagementSystem(String root) throws IOException {

        fs = FileSystems.newFileSystem(Paths.get(root));  
    }

    // Helper functions.
    public String getAbsolutePathFromRelativeFile(String filename) {
        return Paths.get(filename).toAbsolutePath().toString();
    }

    public void printFilePathInformation(Path p) {
        print("Info for path " + p);

        print("Absolute path: " + p.toAbsolutePath());
        print("File name:     " + p.getFileName());
        print("Name elements: " + p.getNameCount());

        for (int i = 0; i < p.getNameCount(); i++) {
            print("| " + p.getName(i));
        }
    }


    public int checkFilePermissions(String file) {
        Path path = Paths.get(file);
        var provider = path.getFileSystem().provider();
        int output = 0;

        // read check
        try {
            provider.checkAccess(path, READ);
            output += 1; // 001
        } catch (IOException e) {
        }

        // write check
        try {
            provider.checkAccess(path, WRITE);
            output += 2; // 010
        } catch (IOException e) {
        }

        // execute check
        try {
            provider.checkAccess(path, EXECUTE);
            output += 4; // 100
        } catch (IOException e) {
        }

        return output;
    }

    public void displayFilePermissions(String file) {
        print("File permissions for " + file);

        byte code = (byte) checkFilePermissions(file);
        
        // god i wish there was an easier way to do this
        if (code == 0) print("X Readable\nX Writable\nX Executable");
        if (code == 1) print("O Readable\nX Writable\nX Executable");
        if (code == 2) print("X Readable\nO Writable\nX Executable");
        if (code == 3) print("O Readable\nO Writable\nX Executable");
        if (code == 4) print("X Readable\nX Writable\nO Executable");
        if (code == 5) print("O Readable\nX Writable\nO Executable");
        if (code == 6) print("X Readable\nO Writable\nO Executable");
        if (code == 7) print("O Readable\nO Writable\nO Executable");
    }

    public void displayFileAttributes(Path filepath) {
        print("File attributes for " + filepath.toString());

        try {
            BasicFileAttributes bfa = Files.readAttributes(filepath, BasicFileAttributes.class);

            print("Created at:    " + bfa.creationTime());
            print("Last modified: " + bfa.lastModifiedTime());
            print("Size:          " + bfa.size() + " byte" + (bfa.size() == 1 ? "" : "s"));
        } catch (IOException e) {

        }
    }

    public void doUserWrite() {
        Scanner s = new Scanner(System.in);
        try {
            prints("Enter filename: ");
            String outputFilename = s.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename));

            print("\nEnter file content:");
            bw.write(s.nextLine());
        } catch (IOException e) {

        }
    }

    public void doUserRead() {
        Scanner s = new Scanner(System.in);
        try {
            prints("Enter filename: ");
            String inputFilename = s.nextLine();

            BufferedReader br = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = br.readLine()) != null) {
                print(line);
            }
        } catch (IOException e) {

        }
    }

    public void exit() {
        loopActive = false;
    }

    // Boilerplate code.
    private void print(String message) {
        System.out.println(message + "");
    }
    private void prints(String message) {
        System.out.print(message + "");
    }
}

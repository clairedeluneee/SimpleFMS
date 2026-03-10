/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplefms;

import java.nio.file.*;

/**
 *
 * @author CompLab209-PC1
 */
public class ManagementSystem {
    private FileSystem fs = FileSystems.getDefault();
    
    public ManagementSystem() {

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



    // Boilerplate code.
    private void print(String message) {
        System.out.println(message + "");
    }
}

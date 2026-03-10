/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplefms;

import java.nio.file.*;
import static java.nio.file.AccessMode.*;

import java.io.*;
import java.io.IOException;

/**
 * The management system itself. This is only the backend, and interfaces with ClefFrontend for the terminal user interface.
 */
public class ManagementSystem {
    private FileSystem fs;
    

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
            output += 1;
        } catch (IOException e) {
        }

        // write check
        try {
            provider.checkAccess(path, WRITE);
        } catch (IOException e) {
        }

        // execute check
        try {
            provider.checkAccess(path, EXECUTE);
        } catch (Exception e) {
        }

        return output;
    }



    // Boilerplate code.
    private void print(String message) {
        System.out.println(message + "");
    }
}

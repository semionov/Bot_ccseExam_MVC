package model;

import java.io.IOException;
import java.util.ArrayList;

public class FilesContainer {

    private static ArrayList<File> files;

    public static void load() throws IOException {
        files = FileService.getFiles();
    }

    public static ArrayList<File> getFiles() {
        return files;
    }
}
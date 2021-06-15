package model;

import java.io.IOException;
import java.util.ArrayList;

public class FileService {


    public static ArrayList<File> getFiles() throws IOException {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("Examen.txt"));
        for (int i = 1; i <= 5; i++) {
            files.add(new File("Tarea" + i + ".txt"));
        }
        return files;
    }

    public static File getTask(int index) throws IOException {
        return FilesContainer.getFiles().get(index);
    }
}

package kento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import kento.commands.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class play {

    private static final Path FILEPATH = Path.of("./.data/kento.txt");

    public static String readTasksFile() {

        try {
            // Reads the entire file into a single String
            String content = Files.readString(FILEPATH);

            if (content != null) {
                return content;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static int taskIdx = 1;

    private static Task[] tasks = new Task[100];

    private static String getTasksFile() {

        String tasksFile = "";
        for (int i = 0; i < taskIdx; i++) {

            // Make a custom in each command class
            tasksFile += task.getTaskIcon() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n";
        }
        return tasksFile;

    }

    public static String writeTasksFiles() {

        try {
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write(getTasksFile());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {

        String content = readTasksFile();
        String[] lines = content.split("\n");
        System.out.print(Arrays.toString(lines));
    }
}

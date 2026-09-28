package kento.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Arrays;
import java.util.Scanner;

import kento.commands.Deadline;
import kento.commands.Event;
import kento.commands.Task;
import kento.commands.Todo;

public class play {

    private static final Path FILEPATH = Path.of("./.data/kento.txt");

    public static String readTasksFiles() {

        try {
            // Reads the entire file into a single String
            String content = Files.readString(FILEPATH);

            return content; // Either string or empty

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void loadTasksFiles() {

        if (!FILEPATH.toFile().exists()) {
            return;
        }

        String content = readTasksFiles();
        if (content.isEmpty()) {
            taskIdx = 0;
            return;
        }
        String[] lines = content.split("\n");

        String[] taskArgs;
        taskIdx = lines.length;

        int loadedCount = 0;
        for (int lineIdx = 0; lineIdx < lines.length; lineIdx++) {
            taskArgs = lines[lineIdx].split("\\|");
            if (taskArgs.length < 2) {
                continue;
            }
            try {
                Task newTask = null;
                switch (taskArgs[0]) {
                    case "T":
                        newTask = new Todo(taskArgs[2]);
                        break;
                    case "D":
                        newTask = new Deadline(taskArgs[2], taskArgs[3]);
                        break;
                    case "E":
                        newTask = new Event(taskArgs[2], taskArgs[3], taskArgs[4]);
                        break;
                    default:
                        System.err.println("Unknown task type: " + taskArgs[0]);
                        continue;
                }
                if (taskArgs[1].equals("X")) {
                    newTask.setIsDone(true);
                }
                tasks[loadedCount] = newTask;
                loadedCount++;
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        taskIdx = loadedCount;

        System.out.print(Arrays.toString(lines));
    }

    private static int taskIdx = 0;

    private static Task[] tasks = new Task[100];
    private static Task task;

    private static String getTasksFiles() {

        String tasksFile = "";
        for (int i = 0; i < taskIdx; i++) {
            task = tasks[i];
            // Make a custom in each command class
            tasksFile += task.getTaskFile() + "\n";
        }
        return tasksFile;

    }

    public static void writeTasksFiles() {

        FileWriter fw = null;
        try {
            File dir = FILEPATH.getParent().toFile();
            dir.mkdirs();
            fw = new FileWriter(FILEPATH.toFile());
            fw.write(getTasksFiles());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadTasksFiles();

        tasks[taskIdx - 1].setIsDone(true);

        tasks[taskIdx] = new Todo("get this over with");
        taskIdx++;
        writeTasksFiles();

    }
}

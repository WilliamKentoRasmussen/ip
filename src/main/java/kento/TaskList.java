package kento;

import java.util.ArrayList;

import kento.commands.Task;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    public static void removeTaskByIndex(int index) {
        tasks.remove(index);
    }

    public static int getTasksSize() {
        return tasks.size();
    }

}

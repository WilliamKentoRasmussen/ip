package kento.commands;

public class Task {

    private boolean isDone;
    protected String description; // Protected give children access to the description

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskIcon() {
        return "T";
    }

    public String getDescription() {
        return this.description; // mark done task with X
    }

}

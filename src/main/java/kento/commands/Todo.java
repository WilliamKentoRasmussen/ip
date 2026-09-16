package kento.commands;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }

    public String getTaskFile() {
        return getTaskIcon() + "|" + getStatusIcon() + "|" + getDescription();
    }
}

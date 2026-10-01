package kento.commands;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.by);
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s)", this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String getTaskFile() {
        return getTaskIcon() + "|" + getStatusIcon() + "|" + this.description + "|" + this.by;
    }
    // Remove when pull request is made
}

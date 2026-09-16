package kento.commands;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public String getDescription() {
        return String.format("%s (from: %s to: %s )", this.description, this.from, this.to);
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    public String getTaskFile() {
        return getTaskIcon() + "|" + getStatusIcon() + "|" + this.description + "|" + this.to + "|" + this.from;
    }
}

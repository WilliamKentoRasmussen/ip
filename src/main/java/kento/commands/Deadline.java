package kento.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;

    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
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
        return getTaskIcon() + "|" + getStatusIcon() + "|" + this.description + "|" + by
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

package kento.ui;

import java.util.Scanner;

import kento.commands.Deadline;
import kento.commands.Event;
import kento.commands.Task;
import kento.commands.Todo;
import kento.exception.CommandException;
import kento.exception.TodoException;

import java.util.Arrays;

public class Kento {
    private static String cmd;
    private static String task;
    private static String secondArg;
    private static String argsCLI;
    private static String[] argsCLIArr;
    private static String[] argsCLIArrSlash;

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static Task t;

    private static int taskIdx = 0;
    private static int inputTaskIdx = 0;

    private static boolean isRunning = true;

    public static void main(String[] args) {

        kentoGreeting();

        Scanner in = new Scanner(System.in);

        while (isRunning) {
            runKentoWithErrorHandling(in);

        }
    }

    private static void runKentoWithErrorHandling(Scanner in) {
        try {
            parseCLI(in);
            executeCmd();
        } catch (CommandException e) {

            System.out.print(ANSI_RED + """
                    ____________________________________________________________
                     OOPSI!!! That command is so wrong. Please do better!
                    ____________________________________________________________
                                    """ + ANSI_RESET);
        } catch (TodoException e) {

            System.out.print(ANSI_RED + """
                    ____________________________________________________________
                    c'mon man. Your todo command is missing a description!
                    ____________________________________________________________
                                    """ + ANSI_RESET);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(ANSI_RED + """
                    ____________________________________________________________
                     Out of bounds error: Please provide an task index or a valid date.
                    ____________________________________________________________
                                    """ + ANSI_RESET);
        } catch (NumberFormatException e) {

            System.out.print(ANSI_RED + """
                    ____________________________________________________________
                     Please provide a passable number instead of string.
                    ____________________________________________________________
                                    """ + ANSI_RESET);
        }

    }

    private static void parseCLI(Scanner in) throws CommandException {
        // TODO: Make a parser class
        argsCLI = in.nextLine();
        argsCLIArr = argsCLI.split(" ");
        argsCLIArrSlash = argsCLI.split("/");

        switch (argsCLIArr.length) {
            case (0):
                throw new CommandException();
            case (1):
                cmd = argsCLIArr[0];
                secondArg = " ";
                break;

            default:
                cmd = argsCLIArr[0];
                secondArg = argsCLIArr[1];
        }

        if ((cmd.length()) == (0))
            throw new CommandException();
    }

    private static void executeCmd() throws TodoException {
        switch (cmd.toLowerCase()) {

            case ("bye"):
                cmdBye(); // static method, so this.cmdBye() is unnecessary
                break;
            case ("list"):
                cmdList();
                break;

            case ("mark"):
                cmdMark();
                break;
            case ("unmark"):
                cmdUnmark();
                break;

            case ("deadline"):
                cmdDeadline();
                break;

            case ("event"):
                cmdEvent();
                break;

            case ("todo"):
                cmdTodo();
                break;

            default:
                cmdDefault();
                break;
        }

    }

    private static void cmdBye() {
        System.out.print("""
                ____________________________________________________________
                 Bye, don't come back without more money!
                ____________________________________________________________
                                """);
        isRunning = false;
    }

    private static void cmdList() {
        System.out.println("____________________________________________________________\n");

        for (int i = 0; i < taskIdx; i++) {
            System.out.println(Integer.toString(i + 1) + ". [" + tasks[i].getStatusIcon() + "]"
                    + tasks[i].getDescription());
        }
        System.out.println("____________________________________________________________");

    }

    private static void cmdMark() throws IndexOutOfBoundsException {
        System.out.println(
                "____________________________________________________________");

        inputTaskIdx = Integer.parseInt(secondArg) - 1;
        if (inputTaskIdx >= taskIdx)
            throw new IndexOutOfBoundsException();
        t = tasks[inputTaskIdx];
        t.setIsDone(true);
        System.out.println("Marked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("____________________________________________________________");
    }

    private static void cmdUnmark() throws IndexOutOfBoundsException {
        System.out.println(
                "____________________________________________________________");

        inputTaskIdx = Integer.parseInt(secondArg) - 1;
        if (inputTaskIdx >= taskIdx)
            throw new IndexOutOfBoundsException();

        t = tasks[inputTaskIdx];
        t.setIsDone(false);
        System.out.println("Unmarked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("____________________________________________________________");
    }

    private static void cmdDeadline() {
        task = argsCLIArrSlash[0].substring(9).strip();
        String by = argsCLIArrSlash[1].substring(2).strip();
        tasks[taskIdx] = new Deadline(task, by);
        taskIdx++;
    }

    private static void cmdTodo() throws TodoException {
        task = argsCLI.substring(5).strip();
        if (task.length() == 0)
            throw new TodoException();
        tasks[taskIdx] = new Todo(task);
        taskIdx++;
    }

    private static void cmdEvent() {
        task = argsCLIArrSlash[0].substring(6).strip();
        String from = argsCLIArrSlash[1].substring(4).strip();
        String to = argsCLIArrSlash[2].substring(2).strip();
        tasks[taskIdx] = new Event(task, from, to);
        taskIdx++;
    }

    private static void kentoGreeting() {
        String banner = " _  __         ____  ___      \n"
                + "| |/ /___ _ __|_  _|/   \\\n"
                + "| ' // _ \\ '_ \\| | / (_) |\n"
                + "|   \\  __/ | | | | \\    |\n"
                + "|_|\\_\\___|_| |_|_|\\_\\___/\n";

        String greeting = """
                ____________________________________________________________
                What is the service you are willing to pay the most for?
                ____________________________________________________________
                """;

        System.out.println("Welcome to");
        System.out.println(banner);
        System.out.println(greeting);
    }

    private static void cmdDefault() {
        System.out.print(String.format("""

                ____________________________________________________________
                Unknown command: %s
                ____________________________________________________________
                                """, cmd));
    }
}

package kento.ui;

import java.util.Scanner;

import kento.commands.Deadline;
import kento.commands.Event;
import kento.commands.Task;
import kento.commands.Todo;
import kento.exception.CommandException;
import kento.exception.TodoException;

import java.util.ArrayList;
import java.util.Arrays;

public class Kento {
    private static String cmd;
    private static String task;
    private static String secondArg;
    private static String argsCLI;
    private static String[] argsCliArr;
    private static String[] argsCliArrSlash;

    // Prints
    public static final String PAGE_LINE = "____________________________________________________________";

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static Task t;

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
        argsCliArr = argsCLI.split(" ");
        argsCliArrSlash = argsCLI.split("/");

        switch (argsCliArr.length) {
            case (0):
                throw new CommandException();
            case (1):
                cmd = argsCliArr[0];
                secondArg = " ";
                break;

            default:
                cmd = argsCliArr[0];
                secondArg = argsCliArr[1];
        }

        if ((cmd.length()) == (0))
            throw new CommandException();
    }

    private static void executeCmd() throws TodoException {
        switch (cmd.toLowerCase()) {

            case "bye":
                executeCmdBye(); // static method, so this.executeCmdBye() is unnecessary
                break;
            case "list":
                executeCmdList();
                break;

            case "delete":
                executeCmdDelete();
                break;

            case "mark":
                executeCmdMark();
                break;
            case "unmark":
                executeCmdUnmark();
                break;

            case "deadline":
                executeCmdDeadline();
                break;

            case "event":
                executeCmdEvent();
                break;

            case "todo":
                executeCmdTodo();
                break;

            default:
                executeCmdDefault();
                break;
        }

    }

    private static void executeCmdBye() {
        System.out.print("""
                ____________________________________________________________
                 Bye, don't come back without more money!
                ____________________________________________________________
                                """);
        isRunning = false;
    }

    private static void executeCmdList() {
        System.out.println("____________________________________________________________\n");

        int i = 0;
        for (Task task : tasks) {
            i++;
            // TODO: Move to commands classes tostring.
            System.out.println(Integer.toString(i) + ". [" + task.getStatusIcon() + "]"
                    + task.getDescription());
        }
        System.out.println("____________________________________________________________");

    }

    private static void executeCmdDelete() throws IndexOutOfBoundsException {
        System.out.println(PAGE_LINE);

        inputTaskIdx = Integer.parseInt(secondArg) - 1;
        if (inputTaskIdx >= tasks.size())
            throw new IndexOutOfBoundsException();
        t = tasks.get(inputTaskIdx);

        System.out.println("Removed \n[" + t.getStatusIcon() + "] " + t.getDescription());
        tasks.remove(inputTaskIdx);
        System.out.println(PAGE_LINE);
    }

    private static void executeCmdMark() throws IndexOutOfBoundsException {
        System.out.println(
                "____________________________________________________________");

        inputTaskIdx = Integer.parseInt(secondArg) - 1;
        if (inputTaskIdx >= tasks.size())
            throw new IndexOutOfBoundsException();
        t = tasks.get(inputTaskIdx);
        t.setIsDone(true);
        System.out.println("Marked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("____________________________________________________________");
    }

    private static void executeCmdUnmark() throws IndexOutOfBoundsException {
        System.out.println(
                "____________________________________________________________");

        inputTaskIdx = Integer.parseInt(secondArg) - 1;
        if (inputTaskIdx >= tasks.size())
            throw new IndexOutOfBoundsException();

        t = tasks.get(inputTaskIdx);
        t.setIsDone(false);
        System.out.println("Unmarked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("____________________________________________________________");
    }

    private static void executeCmdDeadline() {
        task = argsCliArrSlash[0].substring(9).strip();
        String by = argsCliArrSlash[1].substring(2).strip();
        tasks.add(new Deadline(task, by));
    }

    private static void executeCmdTodo() throws TodoException {
        task = argsCLI.substring(5).strip();
        if (task.length() == 0)
            throw new TodoException();
        tasks.add(new Todo(task));
    }

    private static void executeCmdEvent() {
        task = argsCliArrSlash[0].substring(6).strip();
        String from = argsCliArrSlash[1].substring(4).strip();
        String to = argsCliArrSlash[2].substring(2).strip();
        tasks.add(new Event(task, from, to));
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

    private static void executeCmdDefault() {
        System.out.print(String.format("""

                ____________________________________________________________
                Unknown command: %s
                ____________________________________________________________
                                """, cmd));
    }
}

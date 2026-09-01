import java.util.Scanner;

public class Kento {
    private static String cmd;
    private static String task;
    private static String secondArg;
    private static String argsCLI;
    private static String[] argsCLIArr;
    private static String[] argsCLIArrSlash;

    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static Task t;

    private static int taskIdx = 0;

    private static boolean isRunning = true;

    public static void main(String[] args) {

        kentoGreeting();

        Scanner in = new Scanner(System.in);

        while (isRunning) {

            parseCLI(in); // TODO: Make safeguards
            executeCmd();

        }
    }

    private static void parseCLI(Scanner in) {
        // TODO: Make a parser class
        argsCLI = in.nextLine();
        argsCLIArr = argsCLI.split(" ");
        argsCLIArrSlash = argsCLI.split("/");

        switch (argsCLIArr.length) {
            case (0):
                cmd = "";
                secondArg = "";
                break;
            case (1):
                cmd = argsCLIArr[0];
                secondArg = " ";
                break;

            default:
                cmd = argsCLIArr[0];
                secondArg = argsCLIArr[1];
        }
    }

    private static void executeCmd() {
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

    private static void cmdMark() {
        System.out.println(
                "____________________________________________________________");

        t = tasks[Integer.parseInt(secondArg) - 1];
        t.setIsDone(true);
        System.out.println("Marked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("____________________________________________________________");
    }

    private static void cmdUnmark() {
        System.out.println(
                "____________________________________________________________");

        t = tasks[Integer.parseInt(secondArg) - 1];
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

    private static void cmdTodo() {
        task = argsCLI.substring(5).strip();
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

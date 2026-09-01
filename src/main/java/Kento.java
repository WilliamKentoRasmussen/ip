import java.util.Scanner;

public class Kento {

    private static boolean isRunning = true;

    public static void main(String[] args) {

        // Make separate function
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

        Scanner in = new Scanner(System.in);
        String cmd;
        String task;
        String secondArg;
        String argsCLI;

        String[] argsCLIArr;
        String[] argsCLIArrSlash;
        Task[] tasks = new Task[100];
        int taskIdx = 0;

        while (isRunning) {
            argsCLI = in.nextLine();
            argsCLIArr = argsCLI.split(" ");
            argsCLIArrSlash = argsCLI.split("/");
            Task t;

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

            switch (cmd.toLowerCase()) {
                // TODO: Make seperate functions for each
                case ("bye"):
                    System.out.print("""
                            ____________________________________________________________
                             Bye, don't come back without more money!
                            ____________________________________________________________
                                            """);
                    isRunning = false;
                    break;

                case ("list"):
                    System.out.println("____________________________________________________________\n");

                    for (int i = 0; i < taskIdx; i++) {
                        System.out.println(Integer.toString(i + 1) + ". [" + tasks[i].getStatusIcon() + "]"
                                + tasks[i].getDescription());
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case ("mark"):
                    System.out.println(
                            "____________________________________________________________");

                    t = tasks[Integer.parseInt(secondArg) - 1];
                    t.setIsDone(true);
                    System.out.println("Marked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
                    System.out.println("____________________________________________________________");
                    break;

                case ("unmark"):
                    System.out.println(
                            "____________________________________________________________");

                    t = tasks[Integer.parseInt(secondArg) - 1];
                    t.setIsDone(false);
                    System.out.println("Unmarked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
                    System.out.println("____________________________________________________________");
                    break;

                case ("deadline"):
                    task = argsCLIArrSlash[0].substring(9).strip();
                    String by = argsCLIArrSlash[1].substring(2).strip();
                    tasks[taskIdx] = new Deadline(task, by);
                    taskIdx++;
                    break;

                case ("event"):
                    task = argsCLIArrSlash[0].substring(6).strip();
                    String from = argsCLIArrSlash[1].substring(4).strip();
                    String to = argsCLIArrSlash[2].substring(2).strip();
                    tasks[taskIdx] = new Event(task, from, to);
                    taskIdx++;
                    break;

                case ("todo"):
                    task = argsCLI.substring(5).strip();
                    tasks[taskIdx] = new Todo(task);
                    taskIdx++;
                    break;

                default:
                    System.out.print(String.format("""

                            ____________________________________________________________
                            Unknown command: %s
                            ____________________________________________________________
                                            """, cmd));
                    break;
            }

        }
    }
}

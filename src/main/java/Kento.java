import java.util.Scanner;

public class Kento {

    private static boolean running = true;

    public static void main(String[] args) {

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
        String task;
        String secondArg;
        String argsCLI;
        String[] argsCLIArr;
        Task[] tasks = new Task[100];
        int taskIdx = 0;
        while (running) {
            argsCLI = in.nextLine();
            argsCLIArr = argsCLI.split(" ");
            Task t;

            switch (argsCLIArr.length) {
                case (0):
                    task = "";
                    secondArg = "";
                    break;
                case (1):
                    task = argsCLIArr[0];
                    secondArg = " ";
                    break;

                default:
                    task = argsCLIArr[0];
                    secondArg = argsCLIArr[1];
            }

            switch (task.toLowerCase()) {

                case ("bye"):
                    System.out.print("""
                            ____________________________________________________________
                             Bye, don't come back without more money!
                            ____________________________________________________________
                                            """);
                    running = false;
                    break;

                case ("list"):
                    System.out.println("____________________________________________________________");

                    for (int i = 0; i < taskIdx; i++) {
                        System.out.println(Integer.toString(i + 1) + ". [" + tasks[i].getStatusIcon() + "]"
                                + tasks[i].getDescription());
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case ("mark"):
                    System.out.println(
                            "____________________________________________________________");

                    t = tasks[Integer.parseInt(secondArg)];
                    t.setIsDone(true);
                    System.out.println("Marked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
                    System.out.println("____________________________________________________________");
                    break;

                case ("unmark"):
                    System.out.println(
                            "____________________________________________________________");

                    t = tasks[Integer.parseInt(secondArg)];
                    t.setIsDone(false);
                    System.out.println("Unmarked task\n[" + t.getStatusIcon() + "] " + t.getDescription());
                    System.out.println("____________________________________________________________");
                    break;

                default:
                    System.out.print(String.format("""

                            ____________________________________________________________
                            %s
                            ____________________________________________________________
                                            """, task));
                    tasks[taskIdx] = new Task(task);
                    taskIdx++;
                    break;
            }

        }
    }
}

/*
 * Level-1, Level-2, Level-3, A-CodingStandard
 * 
 * Bye.Hope to
 * see you
 * again soon!
 * switch (cmd){
 * switch("list"):
 * System.out.print("");
 * break;
 * }
 * ____________________________________________________________
 */
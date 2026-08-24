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

        String cmd;
        while (running) {
            cmd = in.nextLine();

            switch (cmd.toLowerCase()) {
                case ("bye"):
                    System.out.print("""
                            ____________________________________________________________
                             Bye, don't come back without more money!
                            ____________________________________________________________
                                            """);
                    running = false;
                    break;

                default:
                    System.out.print(String.format("""

                            ____________________________________________________________
                            %s
                            ____________________________________________________________
                                            """, cmd));
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
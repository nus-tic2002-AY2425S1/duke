import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {
        String separator = "____________________________________________________________\n";
        String intro =
                "____________________________________________________________\n" +
                "Hello! I'm Alice, here to make you magically organized!\n" +
                        "How may I help you?\n" +
                "____________________________________________________________";

        String ending =
                "____________________________________________________________\n" +
                "Bye. Back to my wonderland!\n" +
                "____________________________________________________________";

        System.out.println(intro);
        String line;
        Scanner input = new Scanner(System.in);
        line = input.nextLine();
        while(!line.equals("bye")){
            System.out.print(separator + line + "\n" + separator);
            input = new Scanner(System.in);
            line = input.nextLine();
        }
        System.out.println(ending);
    }
}

import java.util.Scanner;

public class Chad {
    public static void main(String[] args) {
        String myline = "_________________________________________________________________";
        String logo = " Chad\n";
        System.out.println(myline);
        System.out.println("Hello from " + logo);
        System.out.println("What can I do for you?\n");
        System.out.println(myline);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye"))
        {
            System.out.println(myline);
            System.out.println("You said: " + line);
            System.out.println(myline);
            line=in.nextLine();
        }
        

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(myline);
    }
}

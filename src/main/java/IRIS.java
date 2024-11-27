import java.util.ArrayList;
import java.util.Scanner;


public class IRIS {
    public static void line() {
        System.out.println("\t____________________________________________________________\n");
    }
    public static void welcome() {
        String logo = "\t _____   _____    _   _____ \n"
                    + "\t|_   _| |  _  \\  | | /  ___| \n"
                    + "\t  | |   | |_| |  | | | |___ \n"
                    + "\t  | |   |  _  /  | | \\___  \\\n"
                    + "\t _| |_  | | \\ \\  | |  ___| | \n"
                    + "\t|_____| |_|  \\_\\ |_| |_____/ \n";
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello, I'm Iris \n" + logo);
        System.out.println("\tWhat can I do for you?\n" + 
                        "\t____________________________________________________________");
    }
    public static void bye() {
        System.out.println("\t____________________________________________________________\n" + 
                        "\tBye. Hope to see you again soon!\r\n" + 
                        "\t____________________________________________________________");
    }
    
    public static void main(String[] args) {
        welcome();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<>();
        while(true) { 
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("bye")){
                bye();
                break;
            }
            else if(input.equalsIgnoreCase("list")){
                line();
                for (int i = 0; i < inputList.size(); i++) { 
                    System.out.println((i+1) + ". "+ inputList.get(i)); 
                }
                line();
            }
            else{
                inputList.add(input);
                System.out.println("\t____________________________________________________________\n" + 
                        '\t' + input + "\t \n" + 
                        "\t____________________________________________________________\n");
            }
        }        
    }
}

import java.util.Scanner;

public class KLbot {
    private static final String botName = "KLbot";
    private static final Scanner in = new Scanner(System.in);
    private static String[] userList = new String[100];
    private static int listCounter = 0;

    public static void main(String[] args) {
        greetUser();
        botLoop();
        sayGoodbye();
        in.close();
    }

    private static void greetUser() {
        printLine();
        System.out.println(" Hey there! I'm " + botName + ", your cheerful helper buddy! \uD83D\uDE0A");
        System.out.println(" What can I do for you today? (Type 'bye' when you're ready to go!)");
        printLine();
    }

    private static void botLoop() {
        while (true) {
            String userInput = in.nextLine();
            if (isExit(userInput)) {
                break;
            }else if(isShowList(userInput)){
                printList();
            }else{
                respondToUser(userInput);
            }
        }
    }

    private static boolean isExit(String userInput) {
        String userExit="bye";
        return userInput.equalsIgnoreCase(userExit);
    }

    private static boolean isShowList(String userInput) {
        String showList="list";
        return userInput.equalsIgnoreCase(showList);
    }

    private static void respondToUser(String userInput) {
        printLine();
        System.out.println("Got it! I’ve added  \"" + userInput + "\" to the list!\uD83D\uDE0A");
        System.out.println("Is there anything else I can help you with? Just let me know!");
        printLine();
        userList[listCounter]=userInput;
        listCounter++;
    }

    public static void printList(){
        printLine();

        for(int i = 0; i < listCounter; i++){
            int listNum=i+1;
            System.out.println("\t"+listNum+"." + userList[i]);
        }

        printLine();
    }

    private static void sayGoodbye() {
        printLine();
        System.out.println("Aww, you're leaving? It was so nice chatting with you! Take care and see you soon! \uD83D\uDC96");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

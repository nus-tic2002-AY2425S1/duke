import java.util.Scanner;



public class Chad {

    public static void chadSay(String chadSay)
    {
        String myline = "_________________________________________________________________";
        System.out.println(myline);
        System.out.println(chadSay);
        System.out.println(myline);
        return;
    }

    public static void printList(String[] inputList){

        // TODO: add your code here
       String myline = "_________________________________________________________________";
       //System.out.println("List:");
            
        System.out.println(myline);
        for(int i=0;i<99;i++)
        {
            if(inputList[i]==null)
            {
                break;
            }
            System.out.println((i+1)+"."+inputList[i]);
        }
        System.out.println(myline);
    }

    public static void main(String[] args) {
        String myline = "_________________________________________________________________";
        String logo = " Chad\n";
        chadSay("Hello from " + logo+"\n"+"What can I do for you?\n");
        String[] inputList = new String[100];
        String line;
        int i=0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye"))
        {

            if(line.equals("list"))
            {
                printList(inputList);
                line=in.nextLine();
                continue;
            }

            chadSay("added: " + line);

            inputList[i]=line;
            i++;
            line=in.nextLine();
            
        }
        
        chadSay("Bye. Hope to see you again soon!");
    }
}

import java.util.Arrays;

public class Tokenize {

   public static void tokenize(String input) throws IllegalArgumentException {
       String[] tokens = input.split(" ");

       if (tokens.length == 1) {
           if (tokens[0].equalsIgnoreCase("todo")
                || tokens[0].equalsIgnoreCase("Deadline")
                || tokens[0].equalsIgnoreCase("Deadline")) {
              throw new IllegalArgumentException(" OOPS!!! \"" + tokens[0].toUpperCase() + "\" description cannot be empty");
           }else if(tokens[0].equalsIgnoreCase("mark") || tokens[0].equalsIgnoreCase("unmark")){
               throw new IllegalArgumentException(" OOPS!!! \"" + tokens[0].toUpperCase() + "\" should include task number");
           }else if( !(tokens[0].equalsIgnoreCase("bye")
                    || tokens[0].equalsIgnoreCase("list"))) {
              throw new IllegalArgumentException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" is not valid query or input )");
          }
       }else{
           if(tokens[0].equalsIgnoreCase("bye")
           || tokens[0].equalsIgnoreCase("list")) {
               throw new IllegalArgumentException(" OOPS!!! ( extra description with \"" + tokens[0].toUpperCase() + "\" is not accepted )");
           } else if (tokens[0].equalsIgnoreCase("deadline")
                    && !(Arrays.asList(tokens).contains("/by"))){
               throw new IllegalArgumentException(" OOPS!!! \"DEADLINE\" tasks need to be done before a specific date/time\n \t\t \"eg: deadline return book /by Sunday\" ");
           }else if (tokens[0].equalsIgnoreCase("event")
                   && !((Arrays.asList(tokens).contains("/from"))
                   && (Arrays.asList(tokens).contains("/to")))){
               throw new IllegalArgumentException(" OOPS!!! \"EVENT\" tasks need to start and ends at a specific date/time\n \t\t \"eg: event project meeting /from Mon 2pm /to 4pm\" ");
           }else if (tokens[0].equalsIgnoreCase("mark") || tokens[0].equalsIgnoreCase("unmark")){
               if(tokens.length > 2){
                   throw new IllegalArgumentException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" is not valid query or input\n \t\t \"eg: mark 2\" )");
               }else {
                   try{
                       int val  = Integer.parseInt(tokens[1]);
                   }catch (NumberFormatException e){
                       throw new NumberFormatException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" didnt have valid task id\n \t\t \"eg: mark 2\" )");
                   }

               }
           }
       }
   }
}

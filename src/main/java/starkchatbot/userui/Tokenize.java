package starkchatbot.userui;

import java.util.Arrays;

public class Tokenize {

   public static String[] tokenize(String input) throws StarkException.InvalidDescriptionException,
                                                        StarkException.InvalidTaskException,
                                                        StarkException.InvalidCommandException {

       String[] tokens = input.split(" ");

       if (tokens.length == 1) {
           if (tokens[0].equalsIgnoreCase("todo")
                || tokens[0].equalsIgnoreCase("Tasks.Deadline")
                || tokens[0].equalsIgnoreCase("Tasks.Deadline")) {
              throw new StarkException.InvalidDescriptionException(" OOPS!!! \"" + tokens[0].toUpperCase() + "\" description cannot be empty");
           }else if(tokens[0].equalsIgnoreCase("mark") || tokens[0].equalsIgnoreCase("unmark")
                   || tokens[0].equalsIgnoreCase("delete")){
               throw new StarkException.InvalidTaskException(" OOPS!!! \"" + tokens[0].toUpperCase() + "\" should include task number");
           }else if( !(tokens[0].equalsIgnoreCase("bye")
                    || tokens[0].equalsIgnoreCase("list"))) {
              throw new StarkException.InvalidCommandException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" is not valid query or input )");
          }
       }else{
           if(tokens[0].equalsIgnoreCase("bye")
           || tokens[0].equalsIgnoreCase("list")) {
               throw new StarkException.InvalidDescriptionException(" OOPS!!! ( extra description with \"" + tokens[0].toUpperCase() + "\" is not accepted )");

           } else if (tokens[0].equalsIgnoreCase("deadline")){
                    if (!(Arrays.asList(tokens).contains("/by"))) {
                        throw new StarkException.InvalidDescriptionException(" OOPS!!! \"DEADLINE\" tasks need to be done before a specific date/time " +
                                                                            System.lineSeparator() +"\t\t \"eg: deadline return book /by Sunday\" ");
                    }
           }else if (tokens[0].equalsIgnoreCase("event")){
               if (!((Arrays.asList(tokens).contains("/from"))
                       && (Arrays.asList(tokens).contains("/to")))) {
                   throw new StarkException.InvalidDescriptionException(" OOPS!!! \"EVENT\" tasks need to start and ends at a specific date/time " +
                                                                        System.lineSeparator() + "\t\t \"eg: event project meeting /from Mon 2pm /to 4pm\" ");
               }
           }else if (tokens[0].equalsIgnoreCase("mark") || tokens[0].equalsIgnoreCase("unmark")
                    || tokens[0].equalsIgnoreCase("delete")){

               String query = tokens[0].toLowerCase();

               if(tokens.length > 2){
                   throw new StarkException.InvalidCommandException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" is not valid query or input" +
                                                                        System.lineSeparator()+ "\t\t \"eg: "+query+" 2\" )");
               }else {
                   try{
                       int val = Integer.parseInt(tokens[1]);
                   }catch (NumberFormatException e){
                       throw new StarkException.InvalidTaskException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" didnt have valid task id" +
                                                                        System.lineSeparator()+"\t\t \"eg: "+query+" 2\" )");
                   }

               }
           }else if (!tokens[0].equalsIgnoreCase("todo")) {
               //all other type of wrong user input throw exception
               throw new StarkException.InvalidCommandException(" OOPS!!! above query is not valid. Please try again...");

           }
       }

       return tokens;
   }
}

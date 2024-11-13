package starkchatbot.userui;

import java.util.Arrays;


/**
 * The  Tokenize class provides a utility method to parse user input strings
 * into tokens for further processing by other classes.
 * This class validates the input string and throws custom exceptions for invalid
 * commands, descriptions, or tasks.
 */
public class Tokenize {

    /**
     * Splits a user input string into tokens and validates them.
     * This method tokenizes the input string based on whitespace and checks for
     * valid commands and required arguments.
     *
     * @param input the input string to be tokenized and validated
     * @return an array of tokens derived from the input string
     * @throws StarkException.InvalidDescriptionException if a description is missing for certain commands
     * @throws StarkException.InvalidTaskException        if a task number is missing for certain commands
     * @throws StarkException.InvalidCommandException     if the command is not recognized
     */
    public static String[] tokenize(String input) throws StarkException.InvalidDescriptionException,
                                                         StarkException.InvalidTaskException,
                                                         StarkException.InvalidCommandException {

        String[] tokens = input.split(" ");

        if (tokens.length == 1) {
            if (tokens[0].equalsIgnoreCase("todo")
                    || tokens[0].equalsIgnoreCase("Deadline")
                    || tokens[0].equalsIgnoreCase("event")
                    ||tokens[0].equalsIgnoreCase("find")) {
                throw new StarkException.InvalidDescriptionException(" OOPS!!! \"" + tokens[0].toUpperCase() + "\" description cannot be empty");
            } else if (tokens[0].equalsIgnoreCase("mark") || tokens[0].equalsIgnoreCase("unmark")
                    || tokens[0].equalsIgnoreCase("delete")) {
                throw new StarkException.InvalidTaskException(" OOPS!!! \"" + tokens[0].toUpperCase() + "\" should include task number");
            } else if (!(tokens[0].equalsIgnoreCase("bye")
                    || tokens[0].equalsIgnoreCase("list")
                    ||tokens[0].equalsIgnoreCase("tentative"))) {
                throw new StarkException.InvalidCommandException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" is not valid query or input )");
            }
            return tokens;
        }

        if (tokens[0].equalsIgnoreCase("bye")
                || tokens[0].equalsIgnoreCase("list")) {
            throw new StarkException.InvalidDescriptionException(" OOPS!!! ( extra description with \"" + tokens[0].toUpperCase() + "\" is not accepted )");

        } else if (tokens[0].equalsIgnoreCase("deadline")) {
            if (!(Arrays.asList(tokens).contains("/by"))) {
                throw new StarkException.InvalidDescriptionException(" OOPS!!! \"DEADLINE\" tasks need to be done before a specific date/time " +
                        System.lineSeparator() + "\t\t \"eg: deadline return book /by 2011-03-11 1700\" ");
            }
        } else if (tokens[0].equalsIgnoreCase("event")) {
            if (!((Arrays.asList(tokens).contains("/from"))
                    && (Arrays.asList(tokens).contains("/to")))) {
                throw new StarkException.InvalidDescriptionException(" OOPS!!! \"EVENT\" tasks need to start and ends at a specific date/time " +
                        System.lineSeparator() + "\t\t \"eg: event project meeting /from 2011-03-11 1700 /to 2011-03-11 1900\" ");
            }
        } else if (tokens[0].equalsIgnoreCase("mark") || tokens[0].equalsIgnoreCase("unmark")
                || tokens[0].equalsIgnoreCase("delete")) {

            String query = tokens[0].toLowerCase();

            if (tokens.length > 2) {
                throw new StarkException.InvalidCommandException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" is not valid query or input" +
                        System.lineSeparator() + "\t\t \"eg: " + query + " 2\" )");
            } else {
                try {
                    int val = Integer.parseInt(tokens[1]);
                } catch (NumberFormatException e) {
                    throw new StarkException.InvalidTaskException(" OOPS!!! ( \"" + tokens[0].toUpperCase() + "\" didnt have valid task id" +
                            System.lineSeparator() + "\t\t \"eg: " + query + " 2\" )");
                }

            }
        } else if (!(tokens[0].equalsIgnoreCase("todo") || tokens[0].equalsIgnoreCase("find"))) {
            //all other type of wrong user input throw exception
            throw new StarkException.InvalidCommandException(" OOPS!!! above query is not valid. Please try again...");

        }


        return tokens;
    }
}

public class Parser {
    public static Command parse(String input) throws SnitchException {
        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else if (input.startsWith("delete")) {
            try {
                return new DeleteCommand(Integer.parseInt(input.substring(7).trim()));
            } catch (NumberFormatException e) {
                throw new SnitchException("Invalid task number format. Please enter a valid number.");
            }
        } else {
            throw new SnitchException("Come on man!!! I don't know what that means.");
        }
    }
}


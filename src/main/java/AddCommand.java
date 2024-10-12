import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddCommand extends Command{

    public AddCommand(String action, String instruction) {
        super(action, instruction);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {

        if (getInstruction().isEmpty())
            throw new NoArgsException("arguments");

        switch (getAction()) {
            case "todo":
                tasks.add(new Todo(getInstruction()));
                break;
            case "deadline":
                if (!getInstruction().contains(" /by "))
                    throw new NoArgsException("/by");

                ArrayList<String> deadlineInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /by ")));
                tasks.add(new Deadline(deadlineInstruction.getFirst(), deadlineInstruction.getLast()));
                break;
            case "event":
                if (!getInstruction().contains(" /from "))
                    throw new NoArgsException("/from");
                else if (!getInstruction().contains(" /to "))
                    throw new NoArgsException("/to");

                ArrayList<String> eventInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /from ")));
                ArrayList<String> eventInstruction2 = new ArrayList<>(Arrays.asList(eventInstruction.getLast().split(" /to ")));

                tasks.add(new Event(eventInstruction.getFirst(), eventInstruction2.getFirst(), eventInstruction2.getLast()));
                break;
        }
        System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
        ui.showSize(tasks.size());

    }
}

//body.append(instruction[1]);
//                try {
//                        while (!instruction[i].equals("/from")) {
//        body.append(" ").append(instruction[i]);
//i++;
//        }
//        }catch (ArrayIndexOutOfBoundsException e){
//separatorMessage("Do include the /from flag!");
//                    break;
//                            }
//i++;
//        try {
//        param.append(instruction[i]);
//                }catch (ArrayIndexOutOfBoundsException e){
//separatorMessage("Do include the day after /from!");
//                    break;
//                            }
//i++;
//        try {
//        while (!instruction[i].equals("/to")) {
//        param.append(" ").append(instruction[i]);
//i++;
//        }
//        }catch (ArrayIndexOutOfBoundsException e){
//separatorMessage("Do include the /to flag!");
//                    break;
//                            }
//i++;
//        try {
//        param2.append(instruction[i]);
//                }catch (ArrayIndexOutOfBoundsException e){
//separatorMessage("Do include the day after /to!");
//                    break;
//                            }
//i++;
//        while(i < instruction.length) {
//        param2.append(" ").append(instruction[i]);
//i++;
//        }
//        tasks.add(new Event(body.toString(), param.toString(), param2.toString()));
//        System.out.print(separator);
//                System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
//        System.out.print("Now you have " + tasks.size() + " in the list." + "\n" + separator);

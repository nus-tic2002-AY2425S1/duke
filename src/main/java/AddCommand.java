import java.io.IOException;
import java.util.ArrayList;

public class AddCommand extends Command{

    public AddCommand(String action, ArrayList<String> instruction) {
        super(action, instruction);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {
        if (getInstruction().isEmpty())
            throw new NoArgsException();

        if (getAction().equals("todo"))
            tasks.add(new Todo(getInstruction().toString()));
        else if (getAction().equals("deadline")){
            if (instruction.length == 1){
                separatorMessage("The description of deadline cannot be empty!");
                break;
            }
            body.append(instruction[1]);
            try {
                while (!instruction[i].equals("/by")) {
                    body.append(" ").append(instruction[i]);
                    i++;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                separatorMessage("Do include the /by flag!");
                break;
            }
            i++;
            try {
                param.append(instruction[i]);
            } catch (ArrayIndexOutOfBoundsException e){
                separatorMessage("Do include the day after /by!");
                break;
            }

            i++;
            while(i < instruction.length) {
                param.append(" ").append(instruction[i]);
                i++;
            }
            tasks.add(new Deadline(body.toString(), param.toString()));
            System.out.print(separator);
            System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
            System.out.print("Now you have " + tasks.size() + " in the list." + "\n" + separator);

        }

        System.out.println("Got it, I've added this task: \n" + getInstruction().toString());
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

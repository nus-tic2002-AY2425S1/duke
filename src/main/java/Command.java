import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    private int index = -1;
    private boolean bool;
    private String action;
    private ArrayList<String> instruction;

    public Command(){
    }

    public Command(int index, boolean bool) {
        this.setIndex(index);
        this.setBool(bool);
    }

    public Command(String action, ArrayList<String> instruction){
        this.setAction(action);
        this.setInstruction(instruction);
    }

    public abstract boolean isExit();
    public abstract void execute(TaskList tasks,Ui ui,Storage storage) throws IOException, NoArgsException;

    public Command(int index){
        this.setIndex(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public ArrayList<String> getInstruction() {
        return instruction;
    }

    public void setInstruction(ArrayList<String> instruction) {
        this.instruction = instruction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
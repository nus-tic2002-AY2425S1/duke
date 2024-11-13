package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Command</h1>
 * The abstract Command class provides a template for
 * all commands that will be utilised by the program.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public abstract class Command {
    private int index = -1;
    private boolean isDone;
    private String action;
    private String instruction;

    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException;

    public Command(){
    }

    public Command(int index, boolean isDone) {
        this.setIndex(index);
        this.setDone(isDone);
    }

    public Command(String action, String instruction){
        this.setAction(action);
        this.setInstruction(instruction);
    }


    public Command(int index){
        this.setIndex(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
package tasks;
public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public Task createTask(String description){
        return new ToDo(description);
    }
    
}

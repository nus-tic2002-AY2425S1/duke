package pistamint.general;

import pistamint.*;

public class Todo extends Task {

    public Todo(String description, char symbol) {
        super(description, symbol);
        this.isDone = false;
    }

    @Override
    public Todo clone() {
        Todo clonedEvent = (Todo) super.clone();
        return clonedEvent;
    }

}

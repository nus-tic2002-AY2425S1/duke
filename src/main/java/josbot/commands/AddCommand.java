package josbot.commands;

import josbot.JosBotException;
import josbot.parser.DateTimeParser;
import josbot.storage.FileStorage;
import josbot.task.*;
import josbot.ui.UI;

public class AddCommand extends Command {

    private Task t;
    private DateTimeParser dt;

    public AddCommand(String commandType, String description) {
        super(commandType, description);
    }

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException
    {
        try{

            if(commandType.equals("todo"))
            {
                t = new Todo(description);
            }
            else if(commandType.equals("deadline"))
            {
                dt = new DateTimeParser();
                String[] deadline_txt = description.split("/by", 2);
                String[] datetime = deadline_txt[1].trim().split(" ");
                if(datetime.length > 1)
                {
                    t = new Deadline(deadline_txt[0].trim(), dt.convertToDateTime(deadline_txt[1].trim()), true);
                }
                else
                {
                    t = new Deadline(deadline_txt[0].trim(), dt.convertToDateTime(deadline_txt[1].trim()), false);
                }

            }
            else if(commandType.equals("event")){
                dt = new DateTimeParser();
                String[] event_txt = description.split("/from", 2);
                String event_name = event_txt[0].trim();
                String[] datetime = event_txt[1].trim().split("/to", 2);


                t = new Event(event_name, dt.convertToDateTime(datetime[0].trim()), dt.convertToDateTime(datetime[1].trim()));
            }
            ui.showAddMessage();
            tasks.addTask(t);
            ui.showTaskMessage(t, tasks);
            file.saveToFile(tasks);
        }
        catch(IndexOutOfBoundsException e)
        {
            ui.showDateTimeError();
        }

    }
}

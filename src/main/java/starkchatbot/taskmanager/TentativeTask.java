package starkchatbot.taskmanager;

import starkchatbot.userui.StarkException;

import java.util.ArrayList;
import java.util.Arrays;

public class TentativeTask extends Task {

    private ArrayList<String> availableSlots = new ArrayList<>();
    private String confirmedSlot = "";
    private boolean isConfirmed = false;


    public TentativeTask(String description) {
        super(description);
    }

    public void addAvailableSlots(String from, String to) {
        try {
            String readableFrom = DateTimeParser.parseDateTime(from);
            String readableTo = DateTimeParser.parseDateTime(to);
            availableSlots.add("(from: " + readableFrom + "   to: " + readableTo + ")");
        } catch (Exception e) {
            throw new StarkException.InvalidCommandException(e.getMessage());
        }
    }

    public void assignAllAvailableSlot(String slots) {
        String[] allSlots = slots.split(", ");
        availableSlots = new ArrayList<>(Arrays.asList(allSlots));
    }

    //to add confirm slot from file storage
    public void assignConfirmedSlot(String slot) {
        if (slot.isEmpty()) {
            return;
        }
        isConfirmed = true;
        confirmedSlot = slot;
    }

    //to confirm slot from commandline UI
    public void setConfirmedSlot(int slotNumber) {
        if (isConfirmed) {
            return;
        }
        if (slotNumber > availableSlots.size()) {
            System.out.println("Slot number must be less than " + availableSlots.size());
        }
        if (slotNumber < 1) {
            System.out.println("Slot number must be greater than 0");
        }
        isConfirmed = true;
        this.confirmedSlot = availableSlots.get(slotNumber - 1);
    }

    public void editConfirmedSlot() {
        if (isConfirmed) {
            isConfirmed = false;
        }
    }

    public void printAllAvailableSlots() {
        int counter = 0;
        System.out.println("\t" + super.getDescription() + ":");
        for (String slot : availableSlots) {
            counter++;
            System.out.println("\t\t" + counter + ") " + slot);
        }
    }

    public String getConfirmedSlot() {
        return confirmedSlot;
    }

    @Override
    public String getDescription() {
        String joinAvailableSlots = String.join(", ", availableSlots);
        return super.getDescription() + " # " + confirmedSlot + " # " + joinAvailableSlots;
    }

    @Override
    public String printTask() {
        if (isConfirmed) {
            return "[TE] " + super.printTask() + " " + confirmedSlot;
        }
        return "[TE] " + super.printTask() + " (Slots not Confirmed yet)";
    }

}

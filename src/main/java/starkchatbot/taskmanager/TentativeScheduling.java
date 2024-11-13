package starkchatbot.taskmanager;

import starkchatbot.userui.StarkException;

import java.util.ArrayList;
import java.util.Arrays;

public class TentativeScheduling extends Task {

    private ArrayList<String> availableSlots = new ArrayList<>();
    private String confirmedSlot = "";
    private boolean isConfirmed = false;


    public TentativeScheduling(String description) {
        super(description);
    }

    public void addAvailableSlots(String from, String to) {
        try {
            String readableFrom = DateTimeParser.parseDateTime(from);
            String readableTo = DateTimeParser.parseDateTime(to);
            availableSlots.add("(from: " + readableFrom + " to: " + readableTo + ")");
        } catch (Exception e) {
            throw new StarkException.InvalidCommandException(e.getMessage());
        }
    }

    public void assignConfirmedSlot(String slot) {
        String[] allSlots = slot.split(", ");
        availableSlots = new ArrayList<>(Arrays.asList(allSlots));
    }

    public void setConfirmedSlot(int slotNumber) {
        if (isConfirmed) {
            System.out.println("Event already confirmed slot: " + confirmedSlot);
            return;
        }
        if (slotNumber > availableSlots.size()) {
            throw new IllegalArgumentException("Slot number must be less than " + availableSlots.size());
        }
        if (slotNumber < 1) {
            throw new IllegalArgumentException("Slot number must be greater than 0");
        }
        isConfirmed = true;
        this.confirmedSlot = availableSlots.get(slotNumber - 1);
    }

    public void editConfirmedSlot(int slotNumber) {
        if (isConfirmed) {
            this.confirmedSlot = availableSlots.get(slotNumber - 1);
        }
    }

    public void printAllAvailableSlots() {
        int counter = 0;
        System.out.println(super.getDescription() + ":");
        for (String slot : availableSlots) {
            counter++;
            System.out.println("\t" + counter + ") " + slot);
        }
    }

    public String getConfirmedSlot() {
        return confirmedSlot;
    }

    @Override
    public String getDescription() {
        String joinAvailableSlots = String.join(", ", availableSlots);
        return super.getDescription() + "|" + confirmedSlot + "|" + " ***" + joinAvailableSlots + "***";
    }

    @Override
    public String printTask() {
        if (isConfirmed) {
            return "[TE] " + super.getDescription() + " " + confirmedSlot ;
        }
        return "[TE] " + super.getDescription() + " (Slots not Confirmed yet)";
    }

}

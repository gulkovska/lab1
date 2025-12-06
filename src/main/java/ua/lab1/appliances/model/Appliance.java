package ua.lab1.appliances.model;

public abstract class Appliance {
    private String name;
    private int power; // watts
    private boolean plugged;
    private Room room;

    public Appliance(String name, int power, Room room) {
        this.name = name;
        this.power = power;
        this.room = room;
        this.plugged = false;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public boolean isPlugged() {
        return plugged;
    }

    public Room getRoom() {
        return room;
    }

    public void plugIn() {
        this.plugged = true;
    }

    public void unplug() {
        this.plugged = false;
    }

    @Override
    public String toString() {
        return name + " (" + power + "W, plugged=" + plugged + ", room=" + room + ")";
    }
}

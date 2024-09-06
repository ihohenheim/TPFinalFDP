package main.java.models;

import java.util.Random;

public class TV {
    private String id;
    private String description;
    private String group;
    private int volume;
    private int channel;
    private boolean isOn;

    public TV(String id, String description) {
        this.id = id;
        this.description = description;
        this.group = id.substring(0, 2);
        this.volume = 3;
        this.channel = 1;
        this.isOn = false;
    }

    public static String generateID() {
        Random random = new Random();
        char letter1 = (char) (random.nextInt(26) + 'A');
        char letter2 = (char) (random.nextInt(26) + 'A');
        int number1 = random.nextInt(10);
        int number2 = random.nextInt(10);
        return "" + letter1 + letter2 + number1 + number2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.group = id.substring(0, 2);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 15) {
            this.volume = volume;
        }
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        if (channel >= 1 && channel <= 999) {
            this.channel = channel;
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public void setPower(boolean power) {
        this.isOn = power;
    }

    // Método togglePower que faltaba
    public void togglePower() {
        this.isOn = !this.isOn;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-10s %-20s %-10s %-7s %-8s",
                id, group, description, isOn ? "Encendido" : "Apagado", channel, volume);
    }
}

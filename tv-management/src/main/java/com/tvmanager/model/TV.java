package com.tvmanager.model;

import java.util.Random;

public class TV {
    private String id;
    private String description;
    private String group;
    private int volume;
    private int channel;
    private boolean isOn;

    public TV(String description, String group) {
        this.id = generateUniqueId();
        this.description = description;
        this.group = group;
        this.volume = 3; // Volumen por defecto
        this.channel = 1; // Canal por defecto
        this.isOn = false; // Apagado por defecto
    }

    private String generateUniqueId() {
        Random random = new Random();
        char letter1 = (char) (random.nextInt(26) + 'A');
        char letter2 = (char) (random.nextInt(26) + 'A');
        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);
        return "" + letter1 + letter2 + digit1 + digit2;
    }

    // Getters y setters para cada atributo

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setGroup(String group) {
        this.group = group;
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

    public void togglePower() {
        this.isOn = !this.isOn;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-10s %-20s %-10s %-7d %-7d",
                id, group, description, isOn ? "Encendido" : "Apagado", channel, volume);
    }
}

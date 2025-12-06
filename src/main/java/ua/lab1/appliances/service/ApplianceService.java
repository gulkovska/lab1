package ua.lab1.appliances.service;

import ua.lab1.appliances.model.Appliance;
import ua.lab1.appliances.model.Room;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApplianceService {

    public int calculateTotalPower(List<Appliance> appliances) {
        return appliances.stream()
                .filter(Appliance::isPlugged)
                .mapToInt(Appliance::getPower)
                .sum();
    }

    public List<Appliance> sortByPowerAsc(List<Appliance> appliances) {
        return appliances.stream()
                .sorted(Comparator.comparingInt(Appliance::getPower))
                .collect(Collectors.toList());
    }

    public List<Appliance> sortByPowerDesc(List<Appliance> appliances) {
        return appliances.stream()
                .sorted((a, b) -> Integer.compare(b.getPower(), a.getPower()))
                .collect(Collectors.toList());
    }

    public List<Appliance> sortByName(List<Appliance> appliances) {
        return appliances.stream()
                .sorted(Comparator.comparing(Appliance::getName))
                .collect(Collectors.toList());
    }

    public List<Appliance> findByPowerRange(List<Appliance> appliances, int min, int max) {
        return appliances.stream()
                .filter(a -> a.getPower() >= min && a.getPower() <= max)
                .collect(Collectors.toList());
    }

    public List<Appliance> findByRoom(List<Appliance> appliances, Room room) {
        return appliances.stream()
                .filter(a -> a.getRoom() == room)
                .collect(Collectors.toList());
    }

    public List<Appliance> findByNamePart(List<Appliance> appliances, String part) {
        String query = part.toLowerCase();
        return appliances.stream()
                .filter(a -> a.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }
}

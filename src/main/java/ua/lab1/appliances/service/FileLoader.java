package ua.lab1.appliances.service;

import ua.lab1.appliances.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public List<Appliance> load(String filePath) {
        List<Appliance> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length < 4) {
                    System.out.println("Некоректний рядок у файлі: " + line);
                    continue;
                }

                String type = parts[0];
                String name = parts[1];
                int power = Integer.parseInt(parts[2]);
                Room room = Room.valueOf(parts[3]);

                switch (type) {
                    case "Kitchen":
                        list.add(new KitchenAppliance(name, power, room));
                        break;
                    case "Cleaning":
                        list.add(new CleaningAppliance(name, power, room));
                        break;
                    case "Entertainment":
                        list.add(new EntertainmentAppliance(name, power, room));
                        break;
                    case "Climate":
                        list.add(new ClimateAppliance(name, power, room));
                        break;
                    default:
                        System.out.println("Невідомий тип приладу: " + type);
                }
            }

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка перетворення кімнати: " + e.getMessage());
        }

        return list;
    }
}

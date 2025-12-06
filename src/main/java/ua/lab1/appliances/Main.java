package ua.lab1.appliances;

import ua.lab1.appliances.menu.ConsoleMenu;
import ua.lab1.appliances.service.FileLoader;
import ua.lab1.appliances.model.Appliance;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileLoader loader = new FileLoader();
        List<Appliance> appliances = loader.load("src/main/resources/appliances.txt");

        if (appliances.isEmpty()) {
            System.out.println("Не вдалося завантажити прилади. Перевірте файл appliances.txt");
            return;
        }

        ConsoleMenu menu = new ConsoleMenu(appliances);
        menu.start();
    }
}

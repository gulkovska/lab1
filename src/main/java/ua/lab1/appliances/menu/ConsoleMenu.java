package ua.lab1.appliances.menu;

import ua.lab1.appliances.model.Appliance;
import ua.lab1.appliances.model.Room;
import ua.lab1.appliances.service.ApplianceService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final ApplianceService service = new ApplianceService();
    private final List<Appliance> appliances;

    public ConsoleMenu(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            System.out.print(">> ");

            while (!sc.hasNextInt()) {
                System.out.print("Введіть номер пункту меню: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    printAppliances();
                    break;
                case 2:
                    plugAppliance(sc);
                    break;
                case 3:
                    unplugAppliance(sc);
                    break;
                case 4:
                    plugAllUnderLimit(sc);
                    break;
                case 5:
                    printTotalPower();
                    break;
                case 6:
                    sortByPowerMenu(sc);
                    break;
                case 7:
                    searchByPowerRange(sc);
                    break;
                case 8:
                    searchByRoom(sc);
                    break;
                case 9:
                    searchByName(sc);
                    break;
                case 0:
                    System.out.println("Вихід з програми...");
                    break;
                default:
                    System.out.println("Невірний пункт меню.");
            }

        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n=== Домашні електроприлади ===");
        System.out.println("1. Показати всі прилади");
        System.out.println("2. Увімкнути прилад за номером");
        System.out.println("3. Вимкнути прилад за номером");
        System.out.println("4. Увімкнути всі прилади з потужністю не більше заданої");
        System.out.println("5. Порахувати загальну споживану потужність (увімкнені прилади)");
        System.out.println("6. Сортувати прилади за потужністю (зростання/спадання)");
        System.out.println("7. Знайти прилади у діапазоні потужності");
        System.out.println("8. Знайти прилади за кімнатою");
        System.out.println("9. Знайти прилади за частиною назви");
        System.out.println("0. Вихід");
    }

    private void printAppliances() {
        System.out.println("\nСписок приладів:");
        for (int i = 0; i < appliances.size(); i++) {
            System.out.println(i + " -> " + appliances.get(i));
        }
    }

    private void plugAppliance(Scanner sc) {
        printAppliances();
        System.out.print("Введіть номер приладу для увімкнення: ");
        int index = sc.nextInt();
        if (index >= 0 && index < appliances.size()) {
            appliances.get(index).plugIn();
            System.out.println("Увімкнено: " + appliances.get(index).getName());
        } else {
            System.out.println("Невірний індекс.");
        }
    }

    private void unplugAppliance(Scanner sc) {
        printAppliances();
        System.out.print("Введіть номер приладу для вимкнення: ");
        int index = sc.nextInt();
        if (index >= 0 && index < appliances.size()) {
            appliances.get(index).unplug();
            System.out.println("Вимкнено: " + appliances.get(index).getName());
        } else {
            System.out.println("Невірний індекс.");
        }
    }

    private void plugAllUnderLimit(Scanner sc) {
        System.out.print("Увімкнути всі прилади з потужністю не більше (W): ");
        int limit = sc.nextInt();
        appliances.stream()
                .filter(a -> a.getPower() <= limit)
                .forEach(Appliance::plugIn);
        System.out.println("Прилади до " + limit + "W увімкнено.");
    }

    private void printTotalPower() {
        int total = service.calculateTotalPower(appliances);
        System.out.println("Загальна потужність увімкнених приладів: " + total + "W");
    }

    private void sortByPowerMenu(Scanner sc) {
        System.out.println("1. За зростанням");
        System.out.println("2. За спаданням");
        System.out.print(">> ");
        int choice = sc.nextInt();
        List<Appliance> sorted;

        if (choice == 1) {
            sorted = service.sortByPowerAsc(appliances);
        } else {
            sorted = service.sortByPowerDesc(appliances);
        }

        sorted.forEach(System.out::println);
    }

    private void searchByPowerRange(Scanner sc) {
        System.out.print("Мінімальна потужність: ");
        int min = sc.nextInt();
        System.out.print("Максимальна потужність: ");
        int max = sc.nextInt();
        service.findByPowerRange(appliances, min, max)
                .forEach(System.out::println);
    }

    private void searchByRoom(Scanner sc) {
        System.out.println("Введіть кімнату (KITCHEN, LIVING_ROOM, BEDROOM, BATHROOM, HALLWAY): ");
        String r = sc.nextLine().trim();
        if (r.isEmpty()) {
            r = sc.nextLine().trim();
        }
        try {
            Room room = Room.valueOf(r);
            service.findByRoom(appliances, room)
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Невірна кімната.");
        }
    }

    private void searchByName(Scanner sc) {
        System.out.print("Введіть частину назви: ");
        String part = sc.nextLine();
        if (part.isEmpty()) {
            part = sc.nextLine();
        }
        service.findByNamePart(appliances, part)
                .forEach(System.out::println);
    }
}

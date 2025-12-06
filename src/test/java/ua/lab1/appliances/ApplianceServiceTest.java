package ua.lab1.appliances;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lab1.appliances.model.*;
import ua.lab1.appliances.service.ApplianceService;

import java.util.Arrays;
import java.util.List;

public class ApplianceServiceTest {

    @Test
    public void testCalculateTotalPower() {
        Appliance kettle = new KitchenAppliance("Kettle", 1500, Room.KITCHEN);
        Appliance vacuum = new CleaningAppliance("Vacuum", 1800, Room.HALLWAY);

        kettle.plugIn();
        vacuum.unplug();

        ApplianceService service = new ApplianceService();
        int total = service.calculateTotalPower(Arrays.asList(kettle, vacuum));

        Assertions.assertEquals(1500, total);
    }

    @Test
    public void testSortByPowerAsc() {
        Appliance a1 = new KitchenAppliance("A1", 500, Room.KITCHEN);
        Appliance a2 = new KitchenAppliance("A2", 100, Room.KITCHEN);
        Appliance a3 = new KitchenAppliance("A3", 300, Room.KITCHEN);

        ApplianceService service = new ApplianceService();
        List<Appliance> sorted = service.sortByPowerAsc(Arrays.asList(a1, a2, a3));

        Assertions.assertEquals(100, sorted.get(0).getPower());
        Assertions.assertEquals(300, sorted.get(1).getPower());
        Assertions.assertEquals(500, sorted.get(2).getPower());
    }

    @Test
    public void testFindByPowerRange() {
        Appliance a1 = new KitchenAppliance("A1", 500, Room.KITCHEN);
        Appliance a2 = new KitchenAppliance("A2", 100, Room.KITCHEN);
        Appliance a3 = new KitchenAppliance("A3", 300, Room.KITCHEN);

        ApplianceService service = new ApplianceService();
        List<Appliance> result = service.findByPowerRange(Arrays.asList(a1, a2, a3), 200, 600);

        Assertions.assertEquals(2, result.size());
    }
}

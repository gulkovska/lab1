package ua.lab1.appliances;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lab1.appliances.model.Appliance;
import ua.lab1.appliances.service.FileLoader;

import java.util.List;

public class FileLoaderTest {

    @Test
    public void testLoad() {
        FileLoader loader = new FileLoader();
        List<Appliance> appliances = loader.load("src/main/resources/appliances.txt");
        Assertions.assertFalse(appliances.isEmpty());
    }
}

package ua.lab1.appliances;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.lab1.appliances.model.Appliance;
import ua.lab1.appliances.service.ApplianceService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockTestsWithMockito {

    @Test
    public void testCalculateTotalPowerWithMock() {
        ApplianceService service = Mockito.mock(ApplianceService.class);

        when(service.calculateTotalPower(anyList())).thenReturn(999);

        int result = service.calculateTotalPower(Collections.<Appliance>emptyList());

        Assertions.assertEquals(999, result);
        verify(service, times(1)).calculateTotalPower(anyList());
    }
}

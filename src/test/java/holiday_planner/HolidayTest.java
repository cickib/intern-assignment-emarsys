package holiday_planner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HolidayTest {

    private Holiday holiday;

    private Destination destinationX;

    @Before
    public void setUp() throws Exception {
        holiday = new Holiday();
        destinationX = new Destination("x");
    }

    @Test(expected = NullPointerException.class)
    public void planRouteWithoutDestinations_throwsNullPointerException() {
        holiday.planRoute();
    }

    @Test
    public void oneDestinationAddedToHoliday_routeSizeIsOne() {
        holiday.addDestination(destinationX);
        holiday.planRoute();
        assertEquals(1, holiday.getRoute().size());
    }

}
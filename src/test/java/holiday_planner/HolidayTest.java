package holiday_planner;

import org.junit.Before;
import org.junit.Test;

public class HolidayTest {

    private Holiday holiday;

    @Before
    public void setUp() throws Exception {
        holiday = new Holiday();
    }

    @Test(expected = NullPointerException.class)
    public void planRouteWithoutDestinations_throwsNullPointerException() {
        holiday.planRoute();
    }

}
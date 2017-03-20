package holiday_planner;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HolidayTest {

    private Holiday holiday;

    private Destination destinationX;
    private Destination destinationY;
    private Destination destinationZ;
    private Destination destinationV;
    private Destination destinationW;
    private Destination destinationU;

    @Before
    public void setUp() throws Exception {
        holiday = new Holiday();

        destinationX = new Destination("x");
        destinationY = new Destination("y");
        destinationZ = new Destination("z");
        destinationV = new Destination("v");
        destinationW = new Destination("w");
        destinationU = new Destination("u");
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

    @Test
    public void multipleDestinations_orderless_firstAddedFirstReturned() {
        holiday.addDestination(destinationY);
        holiday.addDestination(destinationX);
        holiday.addDestination(destinationZ);
        holiday.planRoute();

        assertEquals(destinationY, holiday.getRoute().get(0));
    }

    @Test
    public void multipleDestinations_someOrdered() {
        destinationZ.setNextDestination(destinationY);
        holiday.addDestination(destinationX);
        holiday.addDestination(destinationZ);
        holiday.addDestination(destinationY);
        holiday.planRoute();

        assertTrue(holiday.getRoute().indexOf(destinationZ) < holiday.getRoute().indexOf(destinationY));
    }

    @Test
    public void nextDestinationIsSameAsStartingOne_oneDestinationAdded() {
        destinationU.setNextDestination(destinationU);
        holiday.addDestination(destinationU);
        holiday.planRoute();

        assertEquals(1, holiday.getRoute().size());
    }

    @Test
    public void multipleDestinations_ordered() {
        destinationU.setNextDestination(destinationX);
        destinationX.setNextDestination(destinationZ);
        destinationZ.setNextDestination(destinationW);
        destinationW.setNextDestination(destinationV);
        destinationV.setNextDestination(destinationY);

        holiday.addDestination(destinationU);
        holiday.addDestination(destinationV);
        holiday.addDestination(destinationW);
        holiday.addDestination(destinationZ);
        holiday.addDestination(destinationX);
        holiday.addDestination(destinationY);

        holiday.planRoute();

        List<Destination> alphabetDestinations = Arrays.asList(destinationU, destinationX,
                destinationZ, destinationW, destinationV, destinationY);

        assertEquals(alphabetDestinations, holiday.getRoute());
    }

    @Test
    public void multipleDestinations_paralellOrdered() {
        destinationU.setNextDestination(destinationX);
        destinationZ.setNextDestination(destinationW);
        destinationW.setNextDestination(destinationV);
        destinationV.setNextDestination(destinationY);

        holiday.addDestination(destinationV);
        holiday.addDestination(destinationW);
        holiday.addDestination(destinationX);
        holiday.addDestination(destinationZ);
        holiday.addDestination(destinationY);
        holiday.addDestination(destinationU);

        holiday.planRoute();

        List<Destination> alphabetDestinations = Arrays.asList(destinationZ, destinationW, destinationV, destinationY,
                destinationU, destinationX);

        assertEquals(alphabetDestinations, holiday.getRoute());
    }

    @Test
    public void multipleDestinations_parallelOrdered_lastIsOrderless() {
        destinationW.setNextDestination(destinationU);
        destinationX.setNextDestination(destinationV);
        holiday.addDestination(destinationY);
        holiday.addDestination(destinationV);
        holiday.addDestination(destinationU);
        holiday.addDestination(destinationW);
        holiday.addDestination(destinationX);

        holiday.planRoute();

        assertTrue(holiday.getRoute().indexOf(destinationW) < holiday.getRoute().indexOf(destinationY));
    }

    @Test
    public void sameDestinationAddedMultipleTimes_routeContainsOnlyOnce() {
        destinationU.setNextDestination(destinationW);
        holiday.addDestination(destinationU);
        holiday.addDestination(destinationZ);
        holiday.addDestination(destinationW);
        holiday.addDestination(destinationW);
        holiday.addDestination(destinationW);

        holiday.planRoute();

        assertEquals(1, Collections.frequency(holiday.getRoute(), destinationW));
    }

    @Test
    public void multipleDestinations_connectedInCircle_routeContainsEachOnce() {
        destinationU.setNextDestination(destinationW);
        destinationW.setNextDestination(destinationZ);
        destinationZ.setNextDestination(destinationU);
        holiday.addDestination(destinationZ);
        holiday.addDestination(destinationU);
        holiday.addDestination(destinationW);

        holiday.planRoute();

        assertEquals(3, holiday.getRoute().size());
    }

}
package holiday_planner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class DestinationTest {

    private Destination destinationX;
    private Destination destinationY;
    private Destination destinationZ;

    @Before
    public void setUp() throws Exception {
        destinationX = new Destination("x");
        destinationY = new Destination("y");
        destinationZ = new Destination("z");
    }

    @Test
    public void hasNextDestination_false() {
        assertFalse(destinationX.hasNextDestination());
    }

}
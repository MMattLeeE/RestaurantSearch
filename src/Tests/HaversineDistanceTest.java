package Tests;

import Model.HaversineDistance;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matt on 7/18/2017.
 */
public class HaversineDistanceTest {
    @Test
    public void distanceTest() throws Exception {
        double out = HaversineDistance.distance(38.886369, -77.417127,38.897014, -77.404438);
        System.out.println("distance between two points: " + out + " miles.");

    }

}
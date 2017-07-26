package Tests;

import Model.DataStructures.RestaurantTrip;
import Model.RestaurantModel.Restaurant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matt on 7/26/2017.
 */
public class RestaurantTripTest {
    RestaurantTrip test1 = new RestaurantTrip(new Restaurant(),new Restaurant(),13);
    RestaurantTrip test2 = new RestaurantTrip(new Restaurant(),new Restaurant(),7);

    @Test
    public void compareToTest() throws Exception {
        System.out.println(test1.compareTo(test2));
        System.out.println(test2.compareTo(test1));
    }

}
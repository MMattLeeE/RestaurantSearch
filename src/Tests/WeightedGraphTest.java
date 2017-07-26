package Tests;

import Model.DataStructures.WeightedGraph;
import Model.RestaurantModel.Restaurant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matt on 7/25/2017.
 */
public class WeightedGraphTest {
    WeightedGraph<Restaurant> testWeightGraph = new WeightedGraph<>();

    Restaurant testRestaurant1 = new Restaurant("test1");
    Restaurant testRestaurant2 = new Restaurant("test2");
    Restaurant testRestaurant3 = new Restaurant("test3");
    Restaurant testRestaurant4 = new Restaurant("test4");
    Restaurant testRestaurant5 = new Restaurant("test5");
    Restaurant testRestaurant6 = new Restaurant("test6");
    Restaurant testRestaurant7 = new Restaurant("test7");

    public void addTestRestaurants() throws Exception{
        testWeightGraph.addVertex(testRestaurant1);
        testWeightGraph.addVertex(testRestaurant2);
        testWeightGraph.addVertex(testRestaurant3);
        testWeightGraph.addVertex(testRestaurant4);
        testWeightGraph.addVertex(testRestaurant5);
        testWeightGraph.addVertex(testRestaurant6);
        testWeightGraph.addVertex(testRestaurant7);
    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(testWeightGraph.isEmpty(),true);
        testWeightGraph.addVertex(testRestaurant1);
        assertEquals(testWeightGraph.isEmpty(),false);

    }

    @Test
    public void addVertex() throws Exception {
        testWeightGraph.addVertex(testRestaurant1);
        testWeightGraph.addVertex(testRestaurant2);
        testWeightGraph.addVertex(testRestaurant3);
        testWeightGraph.addVertex(testRestaurant4);
        testWeightGraph.addVertex(testRestaurant5);
        testWeightGraph.addVertex(testRestaurant6);
        testWeightGraph.addVertex(testRestaurant7);

        System.out.println(testWeightGraph.toString());

    }

    @Test
    public void getVertexByIndex() throws Exception {
        addTestRestaurants();

        assertEquals(testRestaurant1,testWeightGraph.getVertexByIndex(0));
    }

    @Test
    public void findVertexIndex() throws Exception {
        addTestRestaurants();

        assertEquals(true,testWeightGraph.findVertexIndex(testRestaurant1)==0);
    }

    @Test
    public void hasVertex() throws Exception {
        addTestRestaurants();

        assertEquals(true,testWeightGraph.hasVertex(testRestaurant1));
        assertEquals(false,testWeightGraph.hasVertex(new Restaurant("test10")));

    }

    @Test
    public void addEdge() throws Exception {
        addTestRestaurants();

        testWeightGraph.addEdge(testRestaurant1,testRestaurant2,1200);
        assertEquals(testWeightGraph.weightIs(testRestaurant1,testRestaurant2)==1200,true);

        testWeightGraph.addEdge(testRestaurant1,testRestaurant3,600);
        testWeightGraph.showPaths();

    }

    @Test
    public void weightIs() throws Exception {
        addTestRestaurants();

        testWeightGraph.addEdge(testRestaurant1,testRestaurant2,1200);
        assertEquals(true,testWeightGraph.weightIs(testRestaurant1,testRestaurant2)==1200);
    }

}
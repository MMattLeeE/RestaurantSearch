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

    public void addTestRestaurants() {
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
    public void getAllVertexes() throws Exception {
    }

    @Test
    public void getVertexByIndex() throws Exception {
    }

    @Test
    public void findVertexIndex() throws Exception {
    }

    @Test
    public void hasVertex() throws Exception {
    }

    @Test
    public void addEdge() throws Exception {
        addTestRestaurants();
        System.out.println(testWeightGraph.getGraphSize());
        testWeightGraph.addEdge(testRestaurant1,testRestaurant2,1200);
        assertEquals(testWeightGraph.weightIs(testRestaurant1,testRestaurant2)==1200,true);
        testWeightGraph.showPaths();
    }

    @Test
    public void weightIs() throws Exception {
    }

}
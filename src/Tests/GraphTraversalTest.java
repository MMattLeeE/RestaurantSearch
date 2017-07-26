package Tests;

import Model.DataStructures.GraphTraversal;
import Model.DataStructures.WeightedGraph;
import Model.RestaurantModel.Restaurant;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matt on 7/26/2017.
 */
public class GraphTraversalTest {
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
    public void createEdges() throws Exception{
        testWeightGraph.addEdge(testRestaurant1,testRestaurant2,100);
        testWeightGraph.addEdge(testRestaurant1,testRestaurant3,100);
        testWeightGraph.addEdge(testRestaurant1,testRestaurant5,100);
        testWeightGraph.addEdge(testRestaurant1,testRestaurant6,100);
        testWeightGraph.addEdge(testRestaurant2,testRestaurant1,100);
        testWeightGraph.addEdge(testRestaurant3,testRestaurant5,100);
        testWeightGraph.addEdge(testRestaurant3,testRestaurant4,100);
        testWeightGraph.addEdge(testRestaurant4,testRestaurant3,100);
        testWeightGraph.addEdge(testRestaurant5,testRestaurant3,100);
        testWeightGraph.addEdge(testRestaurant6,testRestaurant5,100);
        testWeightGraph.addEdge(testRestaurant6,testRestaurant7,100);
        testWeightGraph.addEdge(testRestaurant7,testRestaurant2,100);
        testWeightGraph.addEdge(testRestaurant7,testRestaurant4,100);
    }

    @Test
    public void shortestPathsTest() throws Exception {
        addTestRestaurants();
        createEdges();

        GraphTraversal.shortestPaths(testWeightGraph,testRestaurant5);
        GraphTraversal.shortestPaths(testWeightGraph,testRestaurant1);

    }

}
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
        testWeightGraph.showGraph();

        testWeightGraph.addEdge(testRestaurant1,testRestaurant1,777);
        testWeightGraph.showGraph();

    }

    @Test
    public void weightIs() throws Exception {
        addTestRestaurants();

        testWeightGraph.addEdge(testRestaurant1,testRestaurant2,1200);
        assertEquals(true,testWeightGraph.weightIs(testRestaurant1,testRestaurant2)==1200);
    }

    @Test
    public void visitedTests() throws Exception {
        addTestRestaurants();
        createEdges();

        testWeightGraph.visitVertex(testRestaurant1);
        testWeightGraph.visitVertex(testRestaurant5);
        testWeightGraph.visitVertex(testRestaurant3);
        testWeightGraph.showGraph();

        assertEquals(true,testWeightGraph.isVisited(testRestaurant1));
        assertEquals(false,testWeightGraph.isVisited(testRestaurant2));

        System.out.println();
        for (int i=0; i<testWeightGraph.getNotVisited().size(); i++) {
            System.out.println(testWeightGraph.getNotVisited().get(i).toString());
        }

        testWeightGraph.clearVisited();
        testWeightGraph.showGraph();
    }

    @Test
    public void getEdgesOfTest() throws Exception{
        addTestRestaurants();
        createEdges();
        testWeightGraph.showGraph();
        for (int i=0;i<testWeightGraph.getEdgesOf(testRestaurant1).size(); i++) {
            System.out.println(testWeightGraph.getEdgesOf(testRestaurant1).get(i).getDestinationVertex().getRestaurantName());
        }
    }

    @Test
    public void getGraphSizeTest() throws Exception{
        addTestRestaurants();
        createEdges();

        assertEquals(true,testWeightGraph.getGraphSize()==7);
    }
}
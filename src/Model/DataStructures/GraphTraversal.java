package Model.DataStructures;

import Model.RestaurantModel.Restaurant;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;


/**
 * Created by Matt on 7/26/2017.
 */
public class GraphTraversal {
    public static ArrayList<ArrayList<Restaurant>> shortestPaths(WeightedGraph<Restaurant> graph, Restaurant startVertex) throws PriorityQueueOverflowException, PriorityQueueUnderflowException{
        RestaurantTrip trip;
        RestaurantTrip saveTrip;
        double minDistance;
        double newDistance;
        ArrayList<ArrayList<Restaurant>> output;

        minHeap<RestaurantTrip> pq = new minHeap<>(graph.getGraphSize());
        Restaurant vertex;
        ArrayList<EdgeNode<Restaurant>> edgeArray;

        output = new ArrayList<>();

        graph.clearVisited();
        saveTrip = new RestaurantTrip(startVertex,startVertex,0);
        saveTrip.getVisitedVertexes().add(startVertex);

        pq.enqueue(saveTrip);

        System.out.println("Last Restaurant   Destination   Distance");
        System.out.println("----------------------------------------");

        do {
            trip = pq.dequeue();//dequeue the shortest distance traveled trip

            if (!graph.isVisited(trip.getToVertex())) {//if the vertex that the current trip is going to has not been visited:
                graph.visitVertex(trip.getToVertex());//mark the vertex we are going to as visited


                System.out.println(trip.toString());//print out the trip details as it will be the shortest path to the vertex;
                ArrayList<Restaurant> temp = new ArrayList<>();
                for (int i=0; i<trip.getVisitedVertexes().size();i++) {
                    temp.add(trip.getVisitedVertexes().get(i));
                }
                output.add(temp);


                trip.setFromVertex(trip.getToVertex());//where we are going to, is now where we came from for the trip
                minDistance = trip.getDistance();//store the traveled distance
                edgeArray = graph.getEdgesOf(trip.getFromVertex()); //get the edges of the vertex we are going to
                for (int i=0; i<edgeArray.size();i++) {
                    ArrayList<Restaurant> before = new ArrayList<>();

                    for (int j=0; j<trip.getVisitedVertexes().size(); j++) {
                        before.add(trip.getVisitedVertexes().get(j));
                    }

                    vertex = edgeArray.get(i).getDestinationVertex(); //iterate through each of the edges and set the vertex of where we are going

                    if (!graph.isVisited(vertex)) {//if the edge doesn't lead to a vertex that is already visited:
                        newDistance = minDistance + graph.weightIs(trip.getFromVertex(), vertex);//add the distance to travel to vertex
                        saveTrip = new RestaurantTrip(trip.getFromVertex(), vertex, newDistance);//put all the information into a new trip
                        before.add(vertex);
                        saveTrip.setVisitedVertexes(before);
                        pq.enqueue(saveTrip);//enqueue the trip
                        //the priority queue ensures that the shortest distance traveled comes out first
                        // and all following longer trips to the same vertex get dequeued.
                    }
                }
            }
        } while (!pq.isEmpty());

        //to find any vertexes not reachable, print out everything marked not visited.
        System.out.println();
        System.out.println("The unreachable Vertices:");
        for (int i=0; i<graph.getNotVisited().size(); i++) {
            System.out.println(graph.getNotVisited().get(i).getRestaurantName());
        }
        System.out.println();

        return output;
    }
}

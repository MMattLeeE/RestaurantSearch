package Model.DataStructures;

import Model.RestaurantModel.Restaurant;

import java.util.ArrayList;


/**
 * Created by Matt on 7/26/2017.
 */
public class GraphTraversal {
    public static void shortestPaths(WeightedGraph<Restaurant> graph, Restaurant startVertex) throws PriorityQueueOverflowException, PriorityQueueUnderflowException{
        RestaurantTrip trip;
        RestaurantTrip saveTrip;
        double minDistance;
        double newDistance;

        minHeap<RestaurantTrip> pq = new minHeap<>(graph.getGraphSize());
        Restaurant vertex;
        ArrayList<EdgeNode<Restaurant>> edgeArray;

        graph.clearVisited();
        saveTrip = new RestaurantTrip(startVertex,startVertex,0);
        pq.enqueue(saveTrip);

        System.out.println("Last Restaurant   Destination   Distance");
        System.out.println("----------------------------------------");

        do {
            trip = pq.dequeue();
            if (!graph.isVisited(trip.getToVertex())) {
                graph.visitVertex(trip.getToVertex());
                System.out.println(trip.toString());
                trip.setFromVertex(trip.getToVertex());
                minDistance = trip.getDistance();
                edgeArray = graph.getEdgesOf(trip.getFromVertex());
                for (int i=0; i<edgeArray.size();i++) {
                    vertex = edgeArray.get(i).getDestinationVertex();
                    if (!graph.isVisited(vertex)) {
                        newDistance = minDistance + graph.weightIs(trip.getFromVertex(), vertex);
                        saveTrip = new RestaurantTrip(trip.getFromVertex(), vertex, newDistance);
                        pq.enqueue(saveTrip);
                    }
                }
            }
        } while (!pq.isEmpty());

        System.out.println();
        System.out.println("The unreachable Vertices:");
        for (int i=0; i<graph.getNotVisited().size(); i++) {
            System.out.println(graph.getNotVisited().get(i).getRestaurantName());
        }
        System.out.println();
    }
}

package Model.DataStructures;

import Model.RestaurantModel.Restaurant;

import java.util.ArrayList;

/**
 * Created by Matt on 7/26/2017.
 */
public class RestaurantTrip implements Comparable<RestaurantTrip>{
    private Restaurant fromVertex;
    private Restaurant toVertex;
    private double distance;
    private ArrayList<Restaurant> visitedVertexes;

    public RestaurantTrip(Restaurant fromVertex, Restaurant toVertex, double distance) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.distance = distance;
        visitedVertexes = new ArrayList<>();
    }

    public String toString() {
        return fromVertex.toString() + " " + toVertex.toString() + " " + distance;
    }

    @Override
    public int compareTo(RestaurantTrip o) {
        return Double.valueOf(this.distance).compareTo(o.getDistance());
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double input) {
        this.distance = input;
    }

    public Restaurant getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Restaurant fromVertex) {
        this.fromVertex = fromVertex;
    }

    public Restaurant getToVertex() {
        return toVertex;
    }

    public void setToVertex(Restaurant toVertex) {
        this.toVertex = toVertex;
    }

    public ArrayList<Restaurant> getVisitedVertexes() {
        return visitedVertexes;
    }

    public void setVisitedVertexes(ArrayList<Restaurant> visitedVertexes) {
        this.visitedVertexes = visitedVertexes;
    }
}

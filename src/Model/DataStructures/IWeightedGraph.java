package Model.DataStructures;

/**
 * Created by Matt on 7/24/2017.
 */
public interface IWeightedGraph<E> {
    boolean isEmpty();
    boolean isFull();
    void addVertex(E vertex);
    // Preconditions:   This graph is not full.
    //                  Vertex is not already in this graph.
    //                  Vertex is not null.
    //
    // Adds vertex to this graph.

    boolean hasVertex(E vertex);
    // Returns true if this graph contains vertex; otherwise, returns false.

    void addEdge(E fromVertex, E toVertex, int weight);
    // Adds an edge with the specified weight from fromVertex to toVertex.

    int weightIs(E fromVertex, E toVertex);
    // If edge from fromVertex to toVertex exists, returns the weight of edge;
    // otherwise, returns a special “null-edge” value.

}

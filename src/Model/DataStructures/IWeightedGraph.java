package Model.DataStructures;

/**
 * Created by Matt on 7/24/2017.
 */
public interface IWeightedGraph<E> {
    boolean isEmpty();

    void addVertex(E vertex) throws GraphDuplicateException, GraphNullException;
    // Preconditions:   This graph is not full.
    //                  Vertex is not already in this graph.
    //                  Vertex is not null.
    //
    // Adds vertex to this graph.

    void addEdge(E fromVertex, E toVertex, int weight) throws GraphDuplicateException, GraphNullException;
    // Adds an edge with the specified weight from fromVertex to toVertex.

    double weightIs(E fromVertex, E toVertex);
    // If edge from fromVertex to toVertex exists, returns the weight of edge;
    // otherwise, returns a special “null-edge” value.

}

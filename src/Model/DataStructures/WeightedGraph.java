package Model.DataStructures;

import java.util.ArrayList;

/**
 * Created by Matt on 7/25/2017.
 */
public class WeightedGraph<E extends ICustomCompare<E>> implements IWeightedGraph<E> {
    private ArrayList<GraphNode<E>> vertexes;

    public WeightedGraph() {
        vertexes = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return vertexes.isEmpty();
    }

    public int getGraphSize() {
        return vertexes.size();
    }
    @Override
    public void addVertex(E vertex) {
        GraphNode<E> insertVertex = new GraphNode<>(vertex);
        vertexes.add(insertVertex);
    }

    public ArrayList<GraphNode<E>> getAllVertexes(){
        return vertexes;
    }

    public E getVertexByIndex(int i){
        return vertexes.get(i).getInfo();
    }
    public int findVertexIndex(E search) {
        for (int i=0;i<vertexes.size();i++) {
            if (search.compareBy(vertexes.get(i).getInfo())==0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks the ArrayList of graph nodes to see if the argument (vertex) is contained in the graph.
     * @param vertex
     * @return
     */
    public boolean hasVertex(E vertex){
        boolean isFound = false;
        for (int i = 0; i<vertexes.size(); i++) {
            //if the vertex (Restaurant) is a match by name:
            if ( (vertex.compareBy(vertexes.get(i).getInfo()) )==0 ) {
                isFound = true;
            }
        }
        return isFound;
    }

    @Override
    public void addEdge(E fromVertex, E toVertex, int weight) {
        //if the fromVertex exists in the graph:
        if (hasVertex(fromVertex)) {
            //add edge:
            vertexes.get(findVertexIndex(fromVertex)).addEdge(toVertex,weight);
        } else { //if the vertex is not found in the graph:
            //add the vertex:
            addVertex(fromVertex);
            //add the edge
            vertexes.get(findVertexIndex(fromVertex)).addEdge(toVertex,weight);
        }
    }

    @Override
    public double weightIs(E fromVertex, E toVertex) {
        //if vertex exists in graph:
        if (hasVertex(fromVertex)) {
            if (vertexes.get(findVertexIndex(fromVertex)).hasEdge(toVertex)) {
                return vertexes.get(findVertexIndex(fromVertex)).getEdgeNode(toVertex).getWeight();
            }
        } else {//vertex does not exist in graph:
            System.out.println(fromVertex.toString() + " is not in the graph");
        }
        return -7;
    }

    public String toString() {
        String output="";
        for (int i=0; i <vertexes.size(); i++) {
            output = output + " " + vertexes.get(i).getInfo().toString();
        }
        return output;
    }

    public void showPaths() {
        String output="";
        for (int i=0; i<vertexes.size(); i++) {
            output = output + vertexes.get(i).getEdgeCount() + "|" + vertexes.get(i).getInfo().toString() + " => ";
            for (int j=0; j<vertexes.get(i).getEdgeCount(); i++){
                output = output + " weight: " + vertexes.get(i).getEdgeNode(j).getWeight() + " Destination: " + vertexes.get(i).getEdgeNode(j).getDestinationVertex().toString()+"|";
            }
            output = output + "\n";
        }
        System.out.println(output);
    }
}

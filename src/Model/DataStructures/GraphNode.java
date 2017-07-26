package Model.DataStructures;

import com.sun.javafx.geom.Edge;

import java.util.ArrayList;

/**
 * Created by Matt on 7/25/2017.
 */
public class GraphNode<E extends ICustomCompare<E>> implements ICustomCompare<GraphNode<E>> {
    private E info;
    private ArrayList<EdgeNode<E>> edges;

    public GraphNode() {
        info=null;
        edges = new ArrayList<>();
    }

    public GraphNode(E storeInfo) {
        info = storeInfo;
        edges = new ArrayList<>();
    }

    public E getInfo() {
        return info;
    }

    public ArrayList<EdgeNode<E>> getEdges() {
        return edges;
    }

    public EdgeNode<E> getEdgeNode(E search) {
        return edges.get(findEdgeIndex(search));
    }
    public EdgeNode<E> getEdgeNode(int i) {
        return edges.get(i);
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public boolean hasEdge(E edge) {
        boolean isfound = false;

        for (int i=0;i<edges.size();i++) {
            if (edge.compareBy(edges.get(i).getDestinationVertex())==0) {
                isfound=true;
            }
        }
        return isfound;
    }

    public int findEdgeIndex(E search){
        for (int i=0;i<edges.size();i++) {
            if (search.compareBy(edges.get(i).getDestinationVertex())==0) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(E goToVertex, double weight) {
        //if edge already exists at this vertex:
        if (hasEdge(goToVertex)){
            System.out.println(goToVertex.toString() + " already exists as an edge for " + info.toString());
        } else {
            EdgeNode<E> insertEdge = new EdgeNode<>(goToVertex,weight);
            this.edges.add(insertEdge);
        }
    }

    @Override
    public int compareBy(GraphNode<E> o) {
        return 0;
    }

    @Override
    public int compareBy(GraphNode<E> o, String type) {
        return 0;
    }
}

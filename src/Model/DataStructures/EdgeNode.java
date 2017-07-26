package Model.DataStructures;

/**
 * Created by Matt on 7/25/2017.
 */
public class EdgeNode<E extends ICustomCompare<E>> implements ICustomCompare<EdgeNode<E>> {
    private E destinationVertex;
    private double weight;
    private boolean traveled;

    public EdgeNode() {

    }
    public EdgeNode(E destinationVertex, double weight) {
        this.destinationVertex = destinationVertex;
        this.weight = weight;
        this.traveled = false;
    }

    public E getDestinationVertex() {
        return destinationVertex;
    }

    public void setDestinationVertex(E destinationVertex) {
        this.destinationVertex = destinationVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareBy(EdgeNode<E> o) {
        return 0;
    }

    @Override
    public int compareBy(EdgeNode<E> o, String type) {
        return 0;
    }

    public boolean isTraveled() {
        return traveled;
    }

    public void setTraveled(boolean traveled) {
        this.traveled = traveled;
    }
}

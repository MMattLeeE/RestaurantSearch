package Model.DataStructures;

/**
 * Created by Matt on 7/18/2017.
 */
public interface IPriorityQueue<E extends Comparable<E>> {
    boolean isEmpty();
    boolean isFull();
    void enqueue(E element) throws PriorityQueueOverflowException;
    E dequeue() throws PriorityQueueUnderflowException;
}

package Model.DataStructures;

import MyDataStructures.Exceptions.QueueUnderFlowException;

/**
 * Created by Matt on 6/26/2017.
 */
public interface IBinarySearchTree<E> {
    void add(E object);
    boolean remove(E object);
    boolean contains(E object);
    boolean isEmpty();
    int size();
    String toString();
    E get(E object);
    int reset(int orderType);
    E getNext(int orderType) throws QueueUnderFlowException;
}

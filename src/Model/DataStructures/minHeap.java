package Model.DataStructures;

import java.util.ArrayList;

/**
 * Created by Matt on 7/18/2017.
 */
public class minHeap<E extends Comparable<E>> implements IPriorityQueue<E> {
    private ArrayList<E> elementsArray;
    private int lastIndex;
    private int maxIndex;

    public minHeap(int maxSize) {
        elementsArray = new ArrayList<>(maxSize + 1);
        lastIndex = -1;
        maxIndex = maxSize-1;
    }

    @Override
    public boolean isEmpty() {
        return (lastIndex==-1);
    }

    @Override
    public boolean isFull() {
        return(lastIndex==maxIndex);
    }

    @Override
    public void enqueue(E element) throws PriorityQueueOverflowException {
        if (lastIndex == maxIndex)
            throw new PriorityQueueOverflowException("Priority queue is full");
        else
        {
            lastIndex++;
            elementsArray.add(lastIndex, element);
            reheapUp(element);
        }
    }
    private void reheapUp(E element) {
        int hole = lastIndex;
        while ((hole > 0) && (element.compareTo(elementsArray.get((hole - 1) / 2)) < 0)) {
            elementsArray.set(hole, elementsArray.get((hole - 1) / 2));   // move hole's parent down
            hole = (hole - 1) / 2;                             // move hole up
        }
        elementsArray.set(hole, element);                   // place element into final hole
    }


    @Override
    public E dequeue() throws PriorityQueueUnderflowException {
        E hold;      // element to be dequeued and returned
        E toMove;    // element to move down heap
        if (lastIndex == -1)
            throw new PriorityQueueUnderflowException("Priority queue is empty");
        else
        {
            hold = elementsArray.get(0);              // remember element to be returned
            toMove = elementsArray.remove(lastIndex); // element to reheap down
            lastIndex--;                         // decrease priority queue size
            if (lastIndex != -1)
                reheapDown(toMove);               // restore heap properties
            return hold;                         // return smallest element
        }
    }
    private void reheapDown(E element) {
        int hole = 0;      // current index of hole
        int newhole;       // index where hole should move to
        newhole = newHole(hole, element);   // find next hole
        while (newhole != hole) {
            elementsArray.set(hole, elementsArray.get(newhole));  // move element up
            hole = newhole;                             // move hole down
            newhole = newHole(hole, element);           // find next hole
        }
        elementsArray.set(hole, element);           // fill in the final hole
    }
    private int newHole(int hole, E element)
    // If either child of hole is larger than element return the index
    // of the larger child; otherwise return the index of hole.
    {
        int left = (hole * 2) + 1;
        int right = (hole * 2) + 2;
        if (left > lastIndex)
            // hole has no children
            return hole;
        else
        if (left == lastIndex)
            // hole has left child only
            if (element.compareTo(elementsArray.get(left)) > 0)
                // element < left child
                return left;
            else
                // element >= left child
                return hole;
        else
            // hole has two children
            if (elementsArray.get(left).compareTo(elementsArray.get(right)) > 0)
                // left child < right child
                if (elementsArray.get(right).compareTo(element) >= 0)
                    // right child <= element
                    return hole;
                else
                    // element < right child
                    return right;
            else
                // left child >= right child
                if (elementsArray.get(left).compareTo(element) >= 0)
                    // left child <= element
                    return hole;
                else
                    // element < left child
                    return left;
    }

    public String toString() {
        String output="";

        for (int i=0; i<elementsArray.size(); i++) {
            output += elementsArray.get(i) + " ";
        }

        return output;
    }

}

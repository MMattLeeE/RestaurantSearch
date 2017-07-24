package Tests;

import Model.DataStructures.minHeap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matt on 7/23/2017.
 */
public class minHeapTest {
    @Test
    public void enqueue() throws Exception {
        minHeap testMinHeap = new minHeap(5);

        testMinHeap.enqueue("a");
        testMinHeap.enqueue("c");
        testMinHeap.enqueue("d");
        testMinHeap.enqueue("b");
        testMinHeap.enqueue("e");

        System.out.println(testMinHeap.toString());

        System.out.println(testMinHeap.dequeue().toString());
        System.out.println(testMinHeap.toString());

        System.out.println(testMinHeap.dequeue().toString());
        System.out.println(testMinHeap.toString());

        System.out.println(testMinHeap.dequeue().toString());
        System.out.println(testMinHeap.toString());
    }

}
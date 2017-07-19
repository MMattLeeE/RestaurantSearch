package Model.DataStructures;

import MyDataStructures.Exceptions.ListElementDuplicate;
import MyDataStructures.Exceptions.ListIndexOutOfBounds;
import MyDataStructures.Exceptions.QueueUnderFlowException;
import MyDataStructures.Implementations.NodeIndexed;
import MyDataStructures.Implementations.Queue.QueueUnbounded;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Matt on 6/25/2017.
 */
public class BinarySearchTree<E extends Comparable<E>> implements IBinarySearchTree<E> {
    NodeIndexed<E> root = null;
    boolean found;
    ArrayList<E> holdArray;

    public static final int INORDER = 1;
    public static final int PREORDER = 2;
    public static final int POSTORDER = 3;

    QueueUnbounded<E> inOrderQueue;
    QueueUnbounded<E> preOrderQueue;
    QueueUnbounded<E> postOrderQueue;

    public BinarySearchTree() {
        root = null;
    }

    public NodeIndexed<E> getRoot() {
        return root;
    }

    public QueueUnbounded<E> getInOrderQueue() {
        return inOrderQueue;
    }
    public QueueUnbounded<E> getPreOrderQueue() {
        return preOrderQueue;
    }
    public QueueUnbounded<E> getPostOrderQueue() {
        return postOrderQueue;
    }

    public void setRoot(NodeIndexed<E> newRoot) {
        this.root = newRoot;
    }

    @Override
    public void add (E element) {
        root = recAdd(element, root);
    }
    private NodeIndexed<E> recAdd(E element, NodeIndexed<E> treeNode) {
        if (treeNode == null)
            treeNode = new NodeIndexed<>(element);
        else if (element.compareTo(treeNode.getInfo()) <= 0)
            treeNode.setLeft(recAdd(element, treeNode.getLeft()));
        else
            treeNode.setRight(recAdd(element, treeNode.getRight()));
        return treeNode;
    }


    @Override
    public E get(E element) {
        return recursiveGet(element, root);
    }
    private E recursiveGet(E element, NodeIndexed<E> treeNode) {
        if (treeNode == null)
            return null;
        else if (element.compareTo(treeNode.getInfo()) < 0)
            return recursiveGet(element, treeNode.getLeft());
        else
        if (element.compareTo(treeNode.getInfo()) > 0)
            return recursiveGet(element, treeNode.getRight());
        else
            return treeNode.getInfo();
    }

    @Override
    public boolean contains (E element) {
        return recursiveContains(element, root);
    }
    private boolean recursiveContains(E element, NodeIndexed<E> treeNode) {
        if (treeNode == null)
            return false;
        else if (element.compareTo(treeNode.getInfo()) < 0)
            return recursiveContains(element, treeNode.getLeft());
        else if (element.compareTo(treeNode.getInfo()) > 0)
            return recursiveContains(element, treeNode.getRight());
        else
            return true;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return recursiveSize(root);
    }
    private int recursiveSize(NodeIndexed<E> treeNode) {
        if (treeNode == null)
            return 0;
        else
            return recursiveSize(treeNode.getLeft()) + recursiveSize(treeNode.getRight()) + 1;
    }

    @Override
    public boolean remove(E element) {
        root = recursiveRemove(element, root);
        return found;
    }
    private NodeIndexed<E> recursiveRemove(E element, NodeIndexed<E> treeNode) {
        if (treeNode == null)
            found = false;
        else if (element.compareTo(treeNode.getInfo()) < 0)
            treeNode.setLeft(recursiveRemove(element, treeNode.getLeft()));
        else if (element.compareTo(treeNode.getInfo()) > 0)
            treeNode.setRight(recursiveRemove(element, treeNode.getRight()));
        else
        {
            treeNode = removeNode(treeNode);
            found = true;
        }
        return treeNode;
    }
    private NodeIndexed<E> removeNode(NodeIndexed<E> treeNode) {
        E data;

        if (treeNode.getLeft() == null)
            return treeNode.getRight();
        else if (treeNode.getRight() == null)
            return treeNode.getLeft();
        else
        {
            data = getPredecessor(treeNode.getLeft());
            treeNode.setInfo(data);
            treeNode.setLeft(recursiveRemove(data, treeNode.getLeft()));
            return treeNode;
        }
    }
    private E getPredecessor(NodeIndexed<E> treeNode) {
        while (treeNode.getRight() != null)
            treeNode = treeNode.getRight();
        return treeNode.getInfo();
    }

    @Override
    public int reset(int orderType) {
        int numNodes = size();
        if (orderType == INORDER) {
            inOrderQueue = new QueueUnbounded<>();
            inOrder(root);
        }
        else if (orderType == PREORDER) {
            preOrderQueue = new QueueUnbounded<>();
            preOrder(root);
        }
        if (orderType == POSTORDER) {
            postOrderQueue = new QueueUnbounded<>();
            postOrder(root);
        }
        return numNodes;
    }
    private void inOrder(NodeIndexed<E> treeNode) {
        if (treeNode != null) {
            inOrder(treeNode.getLeft());
            inOrderQueue.enqueue(treeNode.getInfo());
            inOrder(treeNode.getRight());
        }
    }
    private void preOrder(NodeIndexed<E> treeNode) {
        if (treeNode != null) {
            preOrderQueue.enqueue(treeNode.getInfo());
            preOrder(treeNode.getLeft());
            preOrder(treeNode.getRight());
        }
    }
    private void postOrder(NodeIndexed<E> treeNode) {
        if (treeNode != null) {
            postOrder(treeNode.getLeft());
            postOrder(treeNode.getRight());
            postOrderQueue.enqueue(treeNode.getInfo());
        }
    }

    @Override
    public E getNext (int orderType) throws QueueUnderFlowException {
        if (orderType == INORDER) {
            return inOrderQueue.dequeue();
        } else if (orderType == PREORDER) {
            return preOrderQueue.dequeue();
        } else if (orderType == POSTORDER) {
            return postOrderQueue.dequeue();
        } else return null;
    }

    public void balanceTree() throws QueueUnderFlowException, ListElementDuplicate, ListIndexOutOfBounds {
        int count = reset(INORDER);
        holdArray = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            holdArray.add(getNext(INORDER));
        }

        BinarySearchTree<E> newTree = new BinarySearchTree<>();
        newTree.holdArray = this.holdArray;
        newTree.insertTree(0,count - 1);

        this.setRoot(newTree.getRoot()); //set the new balanced tree to the original tree
    }
    private void insertTree(int low, int high) throws ListIndexOutOfBounds{
        if (low == high) {
            this.add(holdArray.get(low));
        } else if((low + 1) == high) {
            this.add(holdArray.get(low));
            this.add(holdArray.get(high));
        } else {
            int mid = (low + high)/2;
            this.add(holdArray.get(mid));
            this.insertTree(low, mid - 1);
            this.insertTree(mid + 1, high);
        }
    }

    public void printTreeStructure() {
        System.out.println();
        recursivePrintTreeStructure(this.root,0);
        System.out.println();
    }
    private void recursivePrintTreeStructure(NodeIndexed<E> root, int level) {
        if(root==null) {
            if(level!=0) {
                for (int i = 0; i < level - 1; i++) {
                    System.out.print("|       ");
                }
                System.out.println("|-------EMPTY");
                return;
            }
        }

        recursivePrintTreeStructure(root.getRight(), level+1);

        if(level!=0){
            for(int i=0;i<level-1;i++) {
                System.out.print("|       ");
            }
            System.out.println("|-------" + root.getInfo());
        } else {
            System.out.println(root.getInfo());
        }
        recursivePrintTreeStructure(root.getLeft(), level+1);
    }

    @Override
    public String toString() {
        if (root != null) {
            return recursiveToString(root);
        } else {
            return "null";
        }
    }
    private String recursiveToString(NodeIndexed<E> root) {
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(recursiveToString(root.getLeft()));
        builder.append(recursiveToString(root.getRight()));
        return builder.append(root.getInfo().toString()).toString();
    }

    public BinarySearchTree<E> reorderBST(Comparator<E> c) throws QueueUnderFlowException, ListIndexOutOfBounds {
        int count = reset(INORDER);
        holdArray = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            holdArray.add(getNext(INORDER));
        }

        Collections.sort(holdArray,c);

        BinarySearchTree<E> newTree = new BinarySearchTree<>();
        newTree.holdArray = this.holdArray;
        newTree.comparatorInsertTree(0,count - 1, c);

        return newTree;
    }
    private void comparatorInsertTree(int low, int high, Comparator<E> c) throws ListIndexOutOfBounds{
        if (low == high) {
            this.comparatorAdd(holdArray.get(low),c);
        } else if((low + 1) == high) {
            this.comparatorAdd(holdArray.get(low),c);
            this.comparatorAdd(holdArray.get(high),c);
        } else {
            int mid = (low + high)/2;
            this.comparatorAdd(holdArray.get(mid),c);
            this.comparatorInsertTree(low, mid - 1,c);
            this.comparatorInsertTree(mid + 1, high,c);
        }
    }
    private void comparatorAdd(E element, Comparator<E> c) {
        root = comparatorRecAdd(element, root, c);
    }
    private NodeIndexed<E> comparatorRecAdd(E element, NodeIndexed<E> treeNode, Comparator<E> c) {
        if (treeNode == null)
            treeNode = new NodeIndexed<>(element);
        else if (c.compare(element,treeNode.getInfo()) <= 0)
            treeNode.setLeft(comparatorRecAdd(element, treeNode.getLeft(),c));
        else
            treeNode.setRight(comparatorRecAdd(element, treeNode.getRight(), c));
        return treeNode;
    }

}

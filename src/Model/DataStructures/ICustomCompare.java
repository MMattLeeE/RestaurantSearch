package Model.DataStructures;

/**
 * Created by Matt on 7/25/2017.
 */
public interface ICustomCompare<E> {
    int compareBy(E o);
    int compareBy(E o, String type);
}

import java.util.ArrayList;
import java.util.Iterator;

public interface Bingeable<T> extends Iterable<T> {
    /**
     * Method to start binge watching the Bingeable object from the start to the end
     */
    public void bingeWatch();
    /**
     * Method to start binge watching the Bingeable object from the start object given for num many time on
     */
    public void bingeWatch(T start, int num);

    /**
     * Extending the Iterable class to "provide a way to obtain to access the elements in any bingeable object"
     */
    public Iterator<T> iterator();
}
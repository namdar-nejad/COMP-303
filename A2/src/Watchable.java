import java.io.IOException;

public interface Watchable {
    /**
     * Method to watch a Wachable Object
     */
    public void watch();

    /**
     * Method to check if a Wachable Object is valid / exists
     * @return True if the object is avalible
     */
    public boolean isValid();

    /**
     * Method to get some info about the Wachable Object
     * @return ralavant information in a string
     */
    public String getInfo();
}
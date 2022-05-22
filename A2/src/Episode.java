
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single episode, with an episode number that can not be changed.
 * Each episode is identified by its path on the file system and can have custom info added to it as a key-value pair.
 * The Episodes class implements Comparable where the compareTo method orders the episodes in increasing order.
 * The class implements teh Watchable interface and provides the necessary methods.
 * The class also implement the Sequential interface and has two extra fields eNext and ePrevious
 * to store the next and the previous values.
 */

public class Episode implements Comparable<Episode>, Watchable, Sequential<Episode>{

    private final File aPath;
    private final int episodeNum;
    private Map<String, String> aTags = new HashMap<>();

    private Episode eNext = null;
    private Episode ePrevious = null;

    /**
     * Overloaded constructor for Episode
     * Creates a episode from the file path.
     *
     * @param aPath
     *            location of the movie on the file system.
     * @param eNum
     *            episode number of the TvShow
     * @pre we are assuming the correct episode number is being provided
     * @throws IllegalArgumentException
     *             if the path doesn't point to a file (e.g., it denotes a directory)
     */
    public Episode(File aPath, int eNum) {
        if (aPath.exists() && !aPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        this.aPath = aPath;
        this.episodeNum = eNum;
    }

    /**
     * Overloaded constructor for Episode
     * Creates a episode from the file path.
     *
     * @param aPath
     *            location of the movie on the file system.
     * @param eNum
     *            episode number of the TvShow
     * @param aTags
     * 			  the cunstom data of the episode
     *
     * @pre we are assuming the correct episode number is being provided
     * @throws IllegalArgumentException
     *             if the path doesn't point to a file (e.g., it denotes a directory)
     */
    public Episode(File aPath, int eNum, Map<String, String> aTags) {
        if (aPath.exists() && !aPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        this.aPath = aPath;
        this.episodeNum = eNum;
        if (aTags != null) this.aTags = aTags;
    }

    /**
     * Sets the value of a custom tag.
     *
     * @param pKey
     *            the key used to retrieve the tag.
     * @param pValue
     *            the value of the tag to insert. Use null to remove the key.
     */
    public void setTag(String pKey, String pValue) {
        if (pValue == null) {
            aTags.remove(pKey);
        }
        else {
            aTags.put(pKey, pValue);
        }
    }

    /**
     * Retrieves the value of a custom tag.
     *
     * @param pKey
     *            the tag key, as it was inserted
     * @return the associated value
     */
    public String getTag(String pKey) {
        return aTags.get(pKey);
    }

    /**
     * @return episode number of the Episode
     */
    public int getNum(){
        return this.episodeNum;
    }


    // Watchable Methods //
    /**
     * Indicates whether this Episode object represents a valid episode that can be played.
     *
     * @return true if the underlying episode file exists and is a file that exists (not a directory)
     */
    @Override
    public boolean isValid() {
        return aPath.isFile();
    }

    /**
     * Method to get some info about the TvShow
     *
     * @return String containing some info
     */
    @Override
    public String getInfo() {
        String rtn = "Episode Number: "+ this.episodeNum + "\n";
        if (!this.aTags.isEmpty()){
            rtn += this.aTags.toString();
            rtn += "\n";
        }
        return rtn;
    }

    /**
     * Plays the Episode
     *
     * @throws AssertionError if the Episode doesn't exist or there is a problem with the file
     */
    @Override
    public void watch() {
        if(this.isValid()){
            System.out.println("Playing" + this.getInfo());
            try {
                Desktop.getDesktop().open(this.aPath);
            } catch (IOException e) {
                throw new AssertionError("Problem with the Episode File");
            }
        }
        else throw new AssertionError("Episode doesn't exist.");
    }

    // Comparable Methods //

    @Override
    /**
     * @param ep the Episode to compare to
     * orders the episodes in increasing order based on their epsidoe number
     */
    public int compareTo(Episode ep) {
        return this.episodeNum - ep.episodeNum;
    }

    // Sequential Methods //

    @Override
    /**
     * Methods to set the next item in the sequence of episodes
     */
    public void setNext(Episode next) {
        this.eNext = next;
    }

    /**
     * Methods to get the next item in the sequence of episodes
     * @return next item in the sequence of episodes, null if has not been assigned
     */
    @Override
    public Episode getNext() {
        return this.eNext;
    }

    /**
     * Methods to set the previous item in the sequence of episodes
     */
    @Override
    public void setPrevious(Episode previous) {
        this.ePrevious = previous;
    }

    /**
     * Methods to get the previous item in the sequence of episodes
     * @return previous item in the sequence of episodes, null if has not been assigned
     */
    @Override
    public Episode getPrevious() {
        return this.ePrevious;
    }
}


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single movie, with at least a title, language, and publishing studio. Each movie is identified by its
 * path on the file system.
 * The class implement the watchable interface.
 */
public class Movie implements Watchable, Sequential<Movie>{

    private final File aPath;

    private String aTitle;
    private String aLanguage;
    private String aStudio;

    private Movie mNext;
    private Movie mPrevious;

    private Map<String, String> aTags = new HashMap<>();

    /**
     * Creates a movie from the file path. Callers must also provide required metadata about the movie.
     *
     * @param pPath
     *            location of the movie on the file system.
     * @param pTitle
     *            official title of the movie in its original language
     * @param pLanguage
     *            language of the movie, in full text (e.g., "English")
     * @param pStudio
     *            studio which originally published the movie
     * @throws IllegalArgumentException
     *             if the path doesn't point to a file (e.g., it denotes a directory)
     */
    public Movie(File pPath, String pTitle, String pLanguage, String pStudio) {
        if (pPath.exists() && !pPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        aPath = pPath; // ok because File is immutable.
        aTitle = pTitle;
        aLanguage = pLanguage;
        aStudio = pStudio;
    }

    public String getTitle() {
        return aTitle;
    }

    public File getPath(){
        return this.aPath;
    }

    public String getLanguage() {
        return aLanguage;
    }

    public String getStudio() {
        return aStudio;
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
     * Indicates whether this Movie object represents a valid movie that can be played.
     *
     * @return true if the underlying video file exists and is a file (not a directory)
     */
    @Override
    public boolean isValid() {
        return aPath.isFile();
    }

    /**
     * Plays the Movie
     *
     * @throws AssertionError if the Movie doesn't exist or is not valid
     */
    @Override
    public void watch(){
        if(this.isValid()){
            System.out.println("Playing" + this.getInfo());
            try {
                Desktop.getDesktop().open(this.aPath);
            } catch (IOException e) {
                throw new AssertionError("Problem with the Movie File");
            }
        }
        else throw new AssertionError("Movie doesn't exist.");
    }

    /**
     * Method to get some info about the Movie
     *
     * @return String containing some info
     */
    @Override
    public String getInfo() {
        String rtn = "Title: "+ this.aTitle + "\n" +
                "Language: "+ this.aLanguage + "\n" +
                "Studio: "+ this.aStudio + "\n";
        if (!this.aTags.isEmpty()){
            String str = this.aTags.toString();
            rtn += str;
        }
        return rtn;
    }

    // Sequential Methods //

    @Override
    public void setNext(Movie next) {
        this.mNext = next;
    }

    @Override
    public Movie getNext() {
        return this.mNext;
    }

    @Override
    public void setPrevious(Movie previous) {
        this.mPrevious = previous;
    }

    @Override
    public Movie getPrevious() {
        return this.mPrevious;
    }
}

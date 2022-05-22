
import java.util.*;

/**
 * Represents a sequence of movies to watch in FIFO order.
 */
public class WatchList implements Bingeable<Movie>{

    private final List<Movie> aList = new LinkedList<>();
    private String aName;

    /**
     * Creates a new empty watchlist.
     *
     * @param pName
     *            the name of the list
     */
    public WatchList(String pName) {
        aName = pName;
    }

    public String getName() {
        return aName;
    }

    /**
     * Changes the name of this watchlist.
     *
     * @param pName
     *            the new name
     */
    public void setName(String pName) {
        aName = pName;
    }

    /**
     * Adds a movie at the end of this watchlist.
     *
     * @param pMovie
     *            the movie to add
     */
    public void addMovie(Movie pMovie) {
        aList.add(pMovie);
    }

    /**
     * Retrieves and removes the next movie to watch from this watchlist. Movies are retrieved in FIFO order.
     */
    public Movie removeNext() {
        return aList.remove(0);
    }

    /**
     * Retrieves the list of movies (valid and invalid) in this watchlist.
     *
     * @return an unmodifiable list of movies, backed by this watchlist
     */
    public List<Movie> getMovies() {
        return Collections.unmodifiableList(aList);
    }

    /**
     * Counts and returns the number of valid movies in this watchlist.
     *
     * @return the number of valid movies
     */
    public int getNumberMovies() {
        int count = 0;
        for (Movie movie : aList) {
            if (movie.isValid()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Retrieves the list of all publishing studios, without duplicates, but including studios of invalid movies.
     *
     * @return a set of studios
     */
    public Set<String> getStudios() {
        Set<String> studios = new HashSet<>();
        for (Movie movie : aList) {
            studios.add(movie.getStudio());
        }
        return studios;
    }

    /**
     * Retrieves the list of all languages, without duplicates, but including languages of invalid movies.
     *
     * @return a set of languages
     */
    public Set<String> getLanguages() {
        Set<String> languages = new HashSet<>();
        for (Movie movie : aList) {
            languages.add(movie.getLanguage());
        }
        return languages;
    }

    // Bingeable Methods //

    /**
     * Method to start binge watching a movie from the start to the end of the Watchlist.
     * This implementation will play all movies at once,
     * I couldn't figure our how to play the next one when the first one ended.
     * But assume that the movies play one after the other.
     * If there is a problem with one of the movie then an AssertionError will be thrown.
     * If the hum is more than the moive left it'll play until the end of the Watchlist and stop.
     */
    @Override
    public void bingeWatch() {
        for (Movie m : aList) {
            try {
                m.watch();
            } catch (AssertionError p) {
                throw new AssertionError("Problem with Movie File" + m.getInfo());
            }
        }
    }

    /**
     * Method to start binge watching a movie between the Movie given and until num other movies after
     * This implementation will play all movies at once,
     * I couldn't figure our how to play the next one when the first one ended.
     * But assume that the movies play one after the other.
     * If there is a problem with one of the movie then an AssertionError will be thrown.
     * If the hum is more than the moive left it'll play until the end of the Watchlist and stop.
     *
     * @param start Movie to start with
     * @param num Number of Movies to go
     */
    @Override
    public void bingeWatch(Movie start, int num) {
        for(int i=aList.indexOf(start); i<num && i<aList.size(); i++){
            try {
                this.aList.get(i).watch();
            } catch (AssertionError p) {
                throw new AssertionError("Problem with Movie File" +  this.aList.get(i).getInfo());
            }
        }
    }

    @Override
    public Iterator<Movie> iterator() {
        return aList.iterator();
    }
}

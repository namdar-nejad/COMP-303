
import java.util.*;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {

    private Set<Movie> aMovies = new HashSet<>();
    private Set<WatchList> aWatchLists = new HashSet<>();
    // added a set of TvShows
    private Set<TvShow> aTvShows = new HashSet<>();

    public Library(){

    }

    /**
     * Adds a movie to the library. Duplicate movies aren't added twice.
     *
     * @param pMovie
     *            the movie to add
     */
    public void addMovie(Movie pMovie) {
        aMovies.add(pMovie);
    }

    /**
     * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
     *
     * @param pList
     *            the watchlist to add
     */
    public void addWatchList(WatchList pList) {
        aWatchLists.add(pList);
        for (Movie movie : pList.getMovies()) {
            addMovie(movie);
        }
    }


    /**
     * Adds a TvShow to the library. Duplicate TvShows aren't added twice.
     *
     * @param pTvShow
     *            the TvShow to add
     */
    public void addTvShow(TvShow pTvShow) {
        aTvShows.add(pTvShow);
    }

    // Generate Methods //
    /**
     * Generate method that generates a ArrayList of Watchable objects depending on the script passed to it.
     * The Generate method calls two private methods depending on the type of script passed to the method.
     *
     * @param script Script object
     * @return
     *      generateMovie will be called if a MovieScript is passed and an ArrayList of movies will be returned
     *      generateEpisode will be called if a EpisodeScript is passed and an ArrayList of episodes will be returned
     *      if no object matches the criteria given the ArrayList will be empty
     */
    public ArrayList<Watchable> generate(Script script){
        if(script instanceof MovieScript) return generateMovie((MovieScript) script);
        else if(script instanceof EpisodeScript) return generateEpisode((EpisodeScript) script);
        else return null;
    }

    /**
     * private method to generate movies
     *
     * @param script movieScript to use
     * @return ArrayList containing matching movies
     */
    private ArrayList<Watchable> generateMovie(MovieScript script){
        ArrayList<Watchable> list = new ArrayList<>(aMovies);
        ArrayList<Watchable> tmp = new ArrayList<>();


        // filter movies by languages //
        for(String l : script.getLangs()){
            for(Watchable m : list){
                if(!((Movie) m).getLanguage().equals(l)){
                    tmp.add(m);
                }
            }
        }

        for(Watchable m : tmp) list.remove(m);
        tmp.clear();


        // filter movies by studio //
        for(String s : script.getStudios()){
            for(Watchable m : list){
                if(!((Movie) m).getStudio().equals(s)) tmp.add(m);
            }
        }

        for(Watchable m : tmp) list.remove(m);
        tmp.clear();

        // filter movies by tag //
        if(script.getTag() != null){
            for(Watchable m : list){
                if(((Movie) m).getTag(script.getTag()) == null) tmp.add(m);
            }
        }

        for(Watchable m : tmp) list.remove(m);
        tmp.clear();

        // filtering by number, removing extra elements to get to number wanted //
        if(script.getNum() != -1){
            Random rand = new Random();
            while(list.size() > script.getNum()){
                list.remove(rand.nextInt(list.size()));
            }
        }

        return list;
    }

    /**
     * private method to generate episodes
     *
     * @param script episodeScript to use
     * @return ArrayList containing matching episodes
     */
    private ArrayList<Watchable> generateEpisode(EpisodeScript script){
        ArrayList<Watchable> list = new ArrayList<>();
        ArrayList<Watchable> tmp = new ArrayList<>();

        // filtering by Tv Show
        if(aTvShows.contains(script.getShow())){
            for(Episode e : script.getShow()){
                 list.add(e);
            }
        }

        // filter episodes by tag //
        if(script.getTag() != null){
            for(Watchable e : list){
                if(((Episode)e).getTag(script.getTag()) == null) tmp.add(e);
            }
        }

        for(Watchable e : tmp) list.remove(e);

        // filtering by number, removing extra elements to get to number wanted //
        if(script.getNum() != -1){
            Random rand = new Random();
            while(list.size() > script.getNum()){
                list.remove(rand.nextInt(list.size()));
            }
        }

        return list;
    }
}

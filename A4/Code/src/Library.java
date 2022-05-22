import java.util.*;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {

	private String aTitle;
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();

	public Library(String pTitle){
		this.aTitle = pTitle;
	}
	
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable movie : pList) {
			addMovie((Movie) movie);
		}
	}
	
	/**
	 * Adds a TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow
	 *            the TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism.
	 * Movies, Episodes, and TVShows are treated individually.
	 * So if both the TVShows and the Episodes inside it pass a filter they
	 * will both be added to the filter result.
	 * 
	 * @param pName
	 *            the name of the watchlist to create
	 * @param pGenerationParameters
	 *            the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListFilter pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		WatchList watchlist = new WatchList(pName);

		// Filter the TVShows
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filterTemplate(show)) {
				watchlist.addWatchable(show);
			}
		}

		// Filter the Episodes
		for (Episode episode : aEpisodes) {
			if (pGenerationParameters.filterTemplate(episode)) {
				watchlist.addWatchable(episode);
			}
		}

		// Filter the Movies
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filterTemplate(movie)) {
				watchlist.addWatchable(movie);
			}
		}
		return watchlist;
	}

	public String toString(){
		String rtn = "";
		rtn += "Printing Library " + aTitle + ": \n";

		rtn += "\tMovies: \n";
		for(Movie m : aMovies){
			rtn += "\t"+ m.toString();
		}

		rtn += "\n\tTv Shows: \n";
		for(TVShow tv : aTVShows){
			rtn += "\t"+ tv.toString();
		}
		rtn += "\n";

		return rtn;
	}
}

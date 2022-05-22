/**
 * Interface for Strategy design pattern
 */
interface WatchListFilter {

	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 *
	 * @param pWatchable
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filterTemplate(Watchable pWatchable);
}
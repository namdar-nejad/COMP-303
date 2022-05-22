public class StudioFilterStrategy implements WatchListFilter {

    private final String aStudio;

    /**
     *
     * @param pStudio Name of the Movie studio of the Watchables to be added
     */
    public StudioFilterStrategy(String pStudio) {
        aStudio = pStudio;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist
     * based on their studio.
     *
     * @param pWatchable
     *            a Watchable to potentially include in the Watchlist
     * @pre pWatchable != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filterTemplate(Watchable pWatchable) {
        assert pWatchable != null;
        return pWatchable.getStudio().equals(aStudio);
    }
}

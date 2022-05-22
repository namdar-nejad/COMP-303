
/*
 * Strategy to filter Episodes whose Episode Number is equal to aNum
 */
public class NthEpisodeFilterStrategy implements WatchListFilter  {
    private final int aNum;

    /**
     * @param pNum the Episode number of the episode to be added, starting from 1
     */
    public NthEpisodeFilterStrategy(int pNum) {
        aNum = pNum;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
     * the Watchable parameter should be an Episode and the episodeNumber should match aNum.
     *
     * @param pWatchable
     *            a Watchable to potentially include in the Watchlist
     * @pre pWatchable != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filterTemplate(Watchable pWatchable) {
        assert pWatchable != null;

        // Only accept Episodes
        if(pWatchable instanceof Episode) {
            // The EpisodeNumbers start from 1
            if (((Episode) pWatchable).getEpisodeNumber() == aNum) return true;
        }

        return false;
    }
}

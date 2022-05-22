
/*
 * Strategy to filter Movies, TVShows or Episodes whose language is equal to pLanguage
 */
public class LanguageFilterStrategy implements WatchListFilter {

    private final Language aLanguage;

    public LanguageFilterStrategy(Language pLanguage) {
        aLanguage = pLanguage;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist
     * based on their Language
     *
     * @param pWatchable
     *            a Watchable to potentially include in the Watchlist
     * @pre pWatchable != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filterTemplate(Watchable pWatchable) {
        assert pWatchable != null;
        return pWatchable.getLanguage().equals(aLanguage);
    }
}
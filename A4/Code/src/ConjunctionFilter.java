import java.util.ArrayList;

/*
 * Composite of WatchListFilter classes, to filter Watchables using logical "and".
 */
public class ConjunctionFilter implements WatchListFilter {

    // ArrayList of filters
    private final ArrayList<WatchListFilter> aElements;

    /**
     * ConjunctionFilter constructor
     * @param pList arrayList of WatchListFilter objects
     */
    public ConjunctionFilter(ArrayList<WatchListFilter> pList){
        aElements = new ArrayList<>(pList);
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist
     * by checking it with all the filters in aElements
     *
     * @param  pWatchable
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * @return true if the Watchable passes all of the filters in aElements, false otherwise
     */
    @Override
    public boolean filterTemplate(Watchable pWatchable) {
        assert pWatchable != null;
        for(WatchListFilter filterElement : aElements){
            // if the Watchable doesn't pass at least one filter, return false
            if(!filterElement.filterTemplate(pWatchable)) return false;
        }
        return true;
    }
}

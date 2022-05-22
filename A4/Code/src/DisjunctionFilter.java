import java.util.ArrayList;

/*
 * Composite of WatchListFilter classes, to filter Watchables using logical "or".
 */
public class DisjunctionFilter implements WatchListFilter {

    // ArrayList of filters
    private final ArrayList<WatchListFilter> aElements;

    /**
     * DisjunctionFilter constructor
     * @param pList arrayList of WatchListFilter objects
     */
    public DisjunctionFilter(ArrayList<WatchListFilter> pList){
        aElements = new ArrayList<>(pList);
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist
     * by checking it with all the filters in aElements
     *
     * @param pWatchable
     *            a Watchable to potentially include in the Watchlist
     * @pre pWatchable != null
     * @return true if the Watchable passes at least one of the filters in aElements, false otherwise
     */
    @Override
    public boolean filterTemplate(Watchable pWatchable) {
        assert pWatchable != null;
        for(WatchListFilter filterElement : aElements){
            // if the Watchable passes one of the filters, return true
            if(filterElement.filterTemplate(pWatchable)) return true;
        }
        return false;
    }
}

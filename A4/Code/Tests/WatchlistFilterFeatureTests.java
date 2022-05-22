import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

/**
 * Unit test for the Watchlist Filtering
 */
public class WatchlistFilterFeatureTests{
    TVShow show1, show2, show3, show4;
    Movie mv1, mv2, mv3, mv4, mv5, mv6, mv7, mv8;
    Library lib1;

    /**
     * Initializes the test fixture.
     */
    @BeforeEach
    public void setup(){

        // Creating some TVShows and Movies
        show1 = new TVShow("Show1", Language.ENGLISH, "WarnerBrothers");
        show1.addEpisode(new File("./ep1_1"), "show 1 ep 1");
        show1.addEpisode(new File("./ep1_2"), "show 1 ep 2");
        show1.addEpisode(new File("./ep1_3"), "show 1 ep 3");
        show1.addEpisode(new File("./ep1_4"), "show 1 ep 4");
        show1.addEpisode(new File("./ep1_5"), "show 1 ep 5");

        show2 = new TVShow("show2", Language.FRENCH, "Disney");
        show2.addEpisode(new File("./ep2_1"), "show 2 ep 1");
        show2.addEpisode(new File("./ep2_2"), "show 2 ep 2");
        show2.addEpisode(new File("./ep2_3"), "show 2 ep 3");
        show2.addEpisode(new File("./ep2_4"), "show 2 ep 4");
        show2.addEpisode(new File("./ep2_5"), "show 2 ep 5");

        show3 = new TVShow("show3", Language.ENGLISH, "WarnerBrothers");
        show3.addEpisode(new File("./ep3_1"), "show 3 ep 1");
        show3.addEpisode(new File("./ep3_2"), "show 3 ep 2");
        show3.addEpisode(new File("./ep3_3"), "show 3 ep 3");
        show3.addEpisode(new File("./ep3_4"), "show 3 ep 4");
        show3.addEpisode(new File("./ep3_5"), "show 3 ep 5");

        show4 = new TVShow("show4", Language.KLINGON, "WarnerBrothers");
        show4.addEpisode(new File("./ep4_1"), "show 4 ep 1");
        show4.addEpisode(new File("./ep4_2"), "show 4 ep 2");
        show4.addEpisode(new File("./ep4_3"), "show 4 ep 3");
        show4.addEpisode(new File("./ep4_4"), "show 4 ep 4");
        show4.addEpisode(new File("./ep4_5"), "show 4 ep 5");

        mv1 = new Movie(new File("./mv1"), "movie1", Language.ENGLISH, "WarnerBrothers");
        mv2 = new Movie(new File("./mv2"), "movie2", Language.FRENCH, "Columbia");
        mv3 = new Movie(new File("./mv3"), "movie3", Language.KLINGON, "WarnerBrothers");
        mv4 = new Movie(new File("./mv4"), "movie4", Language.ENGLISH, "WarnerBrothers");
        mv5 = new Movie(new File("./mv5"), "movie5", Language.FRENCH, "Disney");
        mv6 = new Movie(new File("./mv6"), "movie6", Language.LATIN, "Columbia");
        mv7 = new Movie(new File("./mv7"), "movie7", Language.FRENCH, "Columbia");
        mv8 = new Movie(new File("./mv8"), "movie8", Language.ENGLISH, "WarnerBrothers");

        // Creating a Library and adding the Movies and TVShows to it
        lib1 = new Library("Lib 1");
        lib1.addTVShow(show1);
        lib1.addTVShow(show2);
        lib1.addTVShow(show3);
        lib1.addTVShow(show4);

        lib1.addMovie(mv1);
        lib1.addMovie(mv2);
        lib1.addMovie(mv3);
        lib1.addMovie(mv4);
        lib1.addMovie(mv5);
        lib1.addMovie(mv6);
        lib1.addMovie(mv7);
        lib1.addMovie(mv8);
    }

    /**
     * Testing a pre-built Language Filter.
     */
    @Test
    public void LanguageFilterTest() {
        LanguageFilterStrategy englishFilter = new LanguageFilterStrategy(Language.ENGLISH);
        WatchList englishResult = lib1.generateWatchList("English", englishFilter);
        WatchList expectedResult = new WatchList("English");

        expectedResult.addWatchable(show1);
        expectedResult.addWatchable(show3);

        for(int i=1; i<=show1.getTotalCount(); i++){
            expectedResult.addWatchable(show1.getEpisode(i));
        }
        for(int i=1; i<=show3.getTotalCount(); i++){
            expectedResult.addWatchable(show3.getEpisode(i));
        }
        expectedResult.addWatchable(mv1);
        expectedResult.addWatchable(mv4);
        expectedResult.addWatchable(mv8);

        assertTrue(expectedResult.equals(englishResult));
    }

    /**
     * Testing a pre-built Studio Filter.
     */
    @Test
    public void StudioFilterTest() {
        StudioFilterStrategy warnerFilter = new StudioFilterStrategy("WarnerBrothers");
        WatchList warnerResult = lib1.generateWatchList("Warner Brothers", warnerFilter);
        WatchList expectedResult = new WatchList("Warner Brothers");

        expectedResult.addWatchable(show1);
        expectedResult.addWatchable(show3);
        expectedResult.addWatchable(show4);

        for(int i=1; i<=show1.getTotalCount(); i++){
            expectedResult.addWatchable(show1.getEpisode(i));
        }
        for(int i=1; i<=show3.getTotalCount(); i++){
            expectedResult.addWatchable(show3.getEpisode(i));
        }
        for(int i=1; i<=show4.getTotalCount(); i++){
            expectedResult.addWatchable(show3.getEpisode(i));
        }

        expectedResult.addWatchable(mv1);
        expectedResult.addWatchable(mv3);
        expectedResult.addWatchable(mv4);
        expectedResult.addWatchable(mv8);

        assertTrue(expectedResult.equals(warnerResult));
    }

    /**
     * Testing a Conducted filter of English Language and  WarnerBrothers Studio filters.
     */
    @Test
    public void ConductionFilterTest() {
        LanguageFilterStrategy englishFilter = new LanguageFilterStrategy(Language.ENGLISH);
        StudioFilterStrategy warnerFilter = new StudioFilterStrategy("WarnerBrothers");
        ArrayList<WatchListFilter> tempFilterList = new ArrayList<>();
        tempFilterList.add(warnerFilter); tempFilterList.add(englishFilter);

        ConjunctionFilter engANDwarner = new ConjunctionFilter(tempFilterList);

        WatchList filterResult = lib1.generateWatchList("English AND Warner", engANDwarner);

        WatchList expectedResult = new WatchList("English AND Warner");

        expectedResult.addWatchable(show1);
        expectedResult.addWatchable(show3);

        for(int i=1; i<=show1.getTotalCount(); i++){
            expectedResult.addWatchable(show1.getEpisode(i));
        }
        for(int i=1; i<=show3.getTotalCount(); i++){
            expectedResult.addWatchable(show3.getEpisode(i));
        }

        expectedResult.addWatchable(mv1);
        expectedResult.addWatchable(mv4);
        expectedResult.addWatchable(mv8);

        assertTrue(expectedResult.equals(filterResult));
    }

    /**
     * Testing a Disjunction filter of English OR French Language filters.
     */
    @Test
    public void DisjunctionFilterTest() {
        LanguageFilterStrategy englishFilter = new LanguageFilterStrategy(Language.ENGLISH);
        LanguageFilterStrategy frenchFilter= new LanguageFilterStrategy(Language.FRENCH);
        ArrayList<WatchListFilter> tempFilterList = new ArrayList<>();
        tempFilterList.add(frenchFilter); tempFilterList.add(englishFilter);
        DisjunctionFilter frOReng = new DisjunctionFilter(tempFilterList);

        WatchList filterResult = lib1.generateWatchList("English OR French", frOReng);


        WatchList expectedResult = new WatchList("English OR French");

        expectedResult.addWatchable(show1);
        expectedResult.addWatchable(show2);
        expectedResult.addWatchable(show3);

        for(int i=1; i<=show1.getTotalCount(); i++){
            expectedResult.addWatchable(show1.getEpisode(i));
        }
        for(int i=1; i<=show2.getTotalCount(); i++){
            expectedResult.addWatchable(show2.getEpisode(i));
        }
        for(int i=1; i<=show3.getTotalCount(); i++){
            expectedResult.addWatchable(show3.getEpisode(i));
        }

        expectedResult.addWatchable(mv1);
        expectedResult.addWatchable(mv2);
        expectedResult.addWatchable(mv4);
        expectedResult.addWatchable(mv5);
        expectedResult.addWatchable(mv7);
        expectedResult.addWatchable(mv8);

        assertTrue(expectedResult.equals(filterResult));
    }
}


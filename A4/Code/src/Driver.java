import java.io.File;
import java.security.spec.ECParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args){
        TVShow show1, show2, show3, show4;
        Movie mv1, mv2, mv3, mv4, mv5, mv6, mv7, mv8;
        Library lib1;

        // Creating some TVShows and Movies
        {
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

        // Done with the setup

        /* --------------- */
        /* START OF DEMOS: */
        /* --------------- */


        // Q1 Demo:
        /*
            Generating a Watchlist of the first episodes of all TV shows from the
            WarnerBrothers studio that are in either English or French.

            (WarnerBrothers AND FirstEpisode) AND (French OR English)
         */

        /*
         Step by Step testing of the filters. Please uncomment to see the result of each step of filtering.
         You can skip to the last part if you want to see the final result.
         */

        // Warner Brothers Filter
        StudioFilterStrategy warnerFilter = new StudioFilterStrategy("WarnerBrothers");

//        WatchList warnerResult = lib1.generateWatchList("Warner Brothers", warnerFilter);
//        System.out.println("Warner: \n" + warnerResult.toString());


        // First Episode Filter
        NthEpisodeFilterStrategy firstEpisode = new NthEpisodeFilterStrategy(1);

//        WatchList firstEpisodeResult = lib1.generateWatchList("First Episode", firstEpisode);
//        System.out.println("First Episode: \n" + firstEpisodeResult.toString());


        // French Filter
        LanguageFilterStrategy frenchFilter = new LanguageFilterStrategy(Language.FRENCH);

//        WatchList frenchResult = lib1.generateWatchList("French", frenchFilter);
//        System.out.println("French: \n" + frenchResult.toString());


        // English Filter
        LanguageFilterStrategy englishFilter = new LanguageFilterStrategy(Language.ENGLISH);

//        WatchList englishResult = lib1.generateWatchList("English", englishFilter);
//        System.out.println("English: \n" + englishResult.toString());


        // Warner AND First Episode
        ArrayList<WatchListFilter> tempFilterList = new ArrayList<>();
        tempFilterList.add(warnerFilter); tempFilterList.add(firstEpisode);
        ConjunctionFilter wanerANDfirst = new ConjunctionFilter(tempFilterList);

//        WatchList warnerAndFirstResult = lib1.generateWatchList("Warner AND First Episode", wanerANDfirst);
//        System.out.println("Warner AND First Episode: \n" + warnerAndFirstResult.toString());



        // French OR English
        tempFilterList.clear();
        tempFilterList.add(frenchFilter); tempFilterList.add(englishFilter);
        DisjunctionFilter frOReng = new DisjunctionFilter(tempFilterList);

//        WatchList frenchORenglishResult = lib1.generateWatchList("French OR English", frOReng);
//        System.out.println("French OR English: \n" + frenchORenglishResult.toString());


        /* Final Result of the combined filtering */

        // (WarnerBrothers AND FirstEpisode) AND (French OR English)
        tempFilterList.clear();
        tempFilterList.add(wanerANDfirst); tempFilterList.add(frOReng);
        ConjunctionFilter finalFilter = new ConjunctionFilter(tempFilterList);

        WatchList finalFilterResult = lib1.generateWatchList("(WarnerBrothers AND FirstEpisode) AND (French OR English)", finalFilter);
        System.out.println("filterResult: \n" + finalFilterResult.toString());

    }
}

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The test start from line 100, before that I'm setting up the environment.
 */
public class Driver {
    public static void main(String[] args){


            TvShow show1 = new TvShow("Friends 1");
            TvShow show2 = new TvShow("Friends 2");
            Library lib = new Library();
            // adding Episodes and TvShows to the library
            // Creating Episodes 1
            Map<String, String> e1Tag = new HashMap<>();
            e1Tag.put("Name", "Pilot 1");
            e1Tag.put("Date", "2001/06/21");
            Episode e1 = new Episode(new File("/Users/namdar/Desktop/ch4.pdf"), 1, e1Tag);

            Map<String, String> e2Tag = new HashMap<>();
            e2Tag.put("Name", "The first one 1");
            e2Tag.put("Date", "2001/06/28");
            Episode e2 = new Episode(new File("/Users/namdar/Desktop/ch4 2.pdf"), 2, e2Tag);
            Map<String, String> e3Tag = new HashMap<>();
            e3Tag.put("Name", "The second one 1");
            e3Tag.put("Date", "2001/07/05");
            Episode e3 = new Episode(new File("/Users/namdar/Desktop/ch4.df"), 3, e3Tag);
            Map<String, String> e4Tag = new HashMap<>();
            e4Tag.put("Name", "The third one 1");
            e4Tag.put("IMDB", "8.2");
            Episode e4 = new Episode(new File("/Users/namdar/Desktop/ch4.df"), 4, e4Tag);


            // Tv Show 1

            show1.addEpisode(e1);
            show1.addEpisode(e2);
            show1.addEpisode(e3);
            show1.addEpisode(e4);

            // Creating Episodes 2
            Map<String, String> ee1Tag = new HashMap<>();
            ee1Tag.put("Name", "Pilot 2");
            ee1Tag.put("Date", "2001/06/21");
            Episode ee1 = new Episode(new File("/Users/namdar/Desktop/ch4.pdf"), 1, ee1Tag);
            Map<String, String> ee2Tag = new HashMap<>();
            ee2Tag.put("Name", "The first one 2");
            ee2Tag.put("Date", "2001/06/28");
            Episode ee2 = new Episode(new File("/Users/namdar/Desktop/ch4 2.pdf"), 2, ee2Tag);
            Map<String, String> ee3Tag = new HashMap<>();
            ee3Tag.put("Name", "The second one 2");
            ee3Tag.put("Date", "2001/07/05");
            Episode ee3 = new Episode(new File("/Users/namdar/Desktop/ch4.df"), 3, ee3Tag);
            Map<String, String> ee4Tag = new HashMap<>();
            ee4Tag.put("Name", "The third one 2");
            ee4Tag.put("IMDB", "8.2");
            Episode ee4 = new Episode(new File("/Users/namdar/Desktop/ch4.df"), 4, ee4Tag);

            // Tv Show 2

            show2.addEpisode(ee1);
            show2.addEpisode(ee2);
            show2.addEpisode(ee3);
            show2.addEpisode(ee4);

            // adding Tv Shows to the library
            lib.addTvShow(show1);
            lib.addTvShow(show2);

        // adding Movies and WatchLists to the Library
            Movie m1 = new Movie(new File("/Users/namdar/Desktop/ch4.pdf"), "M1", "English", "Warner Bros");
            Movie m2 = new Movie(new File("/Users/namdar/Desktop/ch4 2.pdf"), "M2", "French", "Warner Bros");
            Movie m3 = new Movie(new File("/Users/namdar/Desktop/ch4 2.pdf"), "M3", "Italian", "Universal");
            Movie m4 = new Movie(new File("/Users/namdar/Desktop/ch4 2.pdf"), "M4", "English", "Universal");
            Movie m5 = new Movie(new File("/Users/namdar/Desktop/ch4 2.pdf"), "M5", "French", "Warner Bros");

            // Watchlist
            WatchList w1 = new WatchList("w1");
            w1.addMovie(m1);
            w1.addMovie(m2);
            w1.addMovie(m3);
            w1.addMovie(m4);
            w1.addMovie(m5);

            // adding moveis to the libaray
            lib.addMovie(m1);
            lib.addMovie(m2);
            lib.addMovie(m3);
            lib.addMovie(m4);
            lib.addMovie(m5);



            // Test Start From Here //

        ////////////////////////////////////////
        // Testing the generator for episodes //
        ////////////////////////////////////////
        {
        // creating episode scripts
        // episode script 1: 2 random episodes from show1 that have "Date" as a Tag
        EpisodeScript epScript1 = new EpisodeScript(show1);
        epScript1.setNum(2);
        epScript1.setTag("Date");

        // episode script 2: 3 random episodes from show2
        EpisodeScript epScript2 = new EpisodeScript(show2);
        epScript2.setNum(3);


        // Printing generated results
        System.out.println("\n\nepisodeScript1: 2 random episodes from show1 that have \"Date\" as a Tag");
        ArrayList<Watchable> episodesGernerated1= lib.generate(epScript1);
        for(Watchable e : episodesGernerated1){
            System.out.println(e.getInfo());
        }

        System.out.println("\n\nmovScript2: 3 random episodes from show2");
        ArrayList<Watchable> episodesGernerated2= lib.generate(epScript2);
        for(Watchable e : episodesGernerated2){
            System.out.println(e.getInfo());
        }

        }

        //////////////////////////////////////
        // Testing the generator for movies //
        //////////////////////////////////////
        {
            // creating episode scripts
            // movie script 1: 1 random french movie
            MovieScript movScript1 = new MovieScript();
            ArrayList<String> langs1 = new ArrayList<String>();
            langs1.add("French");
            movScript1.setLang(langs1);
            movScript1.setNum(1);

            // movie script 2: 2 random movies by Warner Bros
            MovieScript movScript2 = new MovieScript();
            ArrayList<String> studs2 = new ArrayList<String>();
            studs2.add("Warner Bros");
            movScript2.setStudio(studs2);
            movScript2.setNum(2);

            // printing generated results
            System.out.println("\n\nmovScript1: 1 random french movie");
            ArrayList<Watchable> moviesGernerated1 = lib.generate(movScript1);
            for (Watchable m : moviesGernerated1) {
                System.out.println(m.getInfo());
            }

            System.out.println("\n\nmovScript2: 2 random movies by Warner Bros");
            ArrayList<Watchable> moviesGernerated2 = lib.generate(movScript2);
            for (Watchable m : moviesGernerated2) {
                System.out.println(m.getInfo());
            }
        }

        //////////////////////////////////////////////////////
        // Testing some Watchable/Bingeable functionalities //
        //////////////////////////////////////////////////////

        {
//        System.out.println(show1.getInfo());
//        System.out.println(show1.isValid());
//        show1.watch();
//        e1.watch();
//        show1.bingeWatch();
//        show1.bingeWatch(e1, 2);

//        Iterator<Episode> it = show1.iterator();
//        for (Iterator<Episode> iter = it; iter.hasNext(); ) {
//            Episode i = iter.next();
//            System.out.println(i.getInfo());
//        }
        }

        /////////////////////////////////////////////
        // Testing some Sequential functionalities //
        /////////////////////////////////////////////

        {
            e1.setNext(e2);
            e2.setPrevious(e1);
            e2.setNext(e3);
            e3.setPrevious(e2);
            e3.setNext(e4);
            e4.setPrevious(e3);

//            System.out.println(e1.getNext().getInfo());
//            System.out.println(e2.getPrevious().getInfo());
        }

    }
}

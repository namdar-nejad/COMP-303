
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Represents a Tv Show,
 * Each TV Show object has a name and contains a linked list of Episodes.
 * The linked list is always kept sorted using the compare to method in the Episode calss, so that the episodes are
 * always in increasing order of episode number.
 * This class implements the watchable interface. The isValid method checks if all the episode in the movie are valid
 * and watch will start playing the episodes in order from the first episode.
 */
public class TvShow implements Watchable, Bingeable<Episode>{

    private String aTitle;
    private final List<Episode> aEpisodes = new LinkedList<>();

    /**
     * Constructor for the TvShow
     * @param aTitle title of the TvShow
     */
    public TvShow(String aTitle){
        this.aTitle = aTitle;
    }

    /**
     * Adds a Episode to the TvShow. Duplicate Episodes aren't added twice.
     * The list is always kept sorted by increasing order of episode numbers.
     *
     * @param aEpisode
     *            the Episode to add
     */
    public void addEpisode(Episode aEpisode) {
        aEpisodes.add(aEpisode);
        Collections.sort(aEpisodes);
    }

    public String getTitle(){
        return this.aTitle;
    }

    // Bingeable Methods //

    /**
     * Method to start binge watching a movie from the first episode to the last episode
     * This implementation will play all episode at once,
     * I couldn't figure our how to play the next one when the first one ended.
     * But assume that the episodes play one after the other.
     * If there is a problem with one of the episodes then an AssertionError will be thrown.
     */
    @Override
    public void bingeWatch() {
        for (Episode e : aEpisodes) {
            try {
                e.watch();
            } catch (AssertionError p) {
                throw new AssertionError("Problem with Episode File" + e.getInfo());
            }
        }
    }

    /**
     * Method to start binge watching a Episodes starting from the episode given and num other sequential episodes
     * This implementation will play all episode at once,
     * I couldn't figure our how to play the next one when the first one ended.
     * But assume that the episodes play one after the other.
     * If there is a problem with one of the episodes then an AssertionError will be thrown.
     * If the hum is more than the episode left it'll play until the end of the TV Show and stop.
     *
     * @param start Episode to start with
     * @param num Number of episode to go
     */
    @Override
    public void bingeWatch(Episode start, int num) {
        for(int i=aEpisodes.indexOf(start); i<num && i<aEpisodes.size(); i++){
            try {
                this.aEpisodes.get(i).watch();
            } catch (AssertionError p) {
                throw new AssertionError("Problem with Episode File" +  this.aEpisodes.get(i).getInfo());
            }
        }
    }

    @Override
    public Iterator<Episode> iterator() {
        return aEpisodes.iterator();
    }

    // Watchable Methods //
    /**
     * Method to start watching a movie
     * It plays the first episode of the Tv Show
     */
    @Override
    public void watch() {
            try {
                this.aEpisodes.get(0).watch();
            } catch (AssertionError p) {
                throw new AssertionError("Problem with Episode File" +  this.aEpisodes.get(0).getInfo());
            }
    }

    /**
     * Method to check if a TvShow is valid
     * a TvShow is valid if all it's episode are valid
     *
     * @return True of all the episodes of the TvShow are valid
     */
    @Override
    public boolean isValid() {
        boolean rtn = true;
        for(Episode e : aEpisodes){
            if(!e.isValid()){
                rtn = false;
                break;
            }
        }
        return rtn;
    }

    /**
     * Method to get some info about the TvShow
     *
     * @return String containing some info
     */
    @Override
    public String getInfo() {
        String rtn = "Title: "+ this.aTitle + "\nEpisodes: \n";
        if (!this.aEpisodes.isEmpty()){
            for(Episode e : this.aEpisodes) {
                String str = e.getInfo();
                rtn += str;
            }
            rtn += "\n";
        }
        return rtn;
    }
}
import java.io.File;
import java.util.*;

/**
 * Represents a single TV show, with at least a title, language, and publishing studio. Each TVShow aggregates episodes.
 */
public class TVShow implements Watchable, Bingeable<Episode>, Cloneable {

	// New Code Starts Here //

	// the Episode prototype created by user, initialized to a default Episode
	private Episode episodePrototype =
			new Episode(new File("default path"),this, "default Title", -1);

	/**
	 * Method to set a new Episode prototype
	 * @param pCast Map with String key-value pairs with the cast info to store in the prototype Episode
	 *              Pass an empty initialized Map if you want this field to be empty.
	 * @param pInfo Map with String key-value pairs with the Episode info to store in the prototype Episode
	 *              Pass an empty initialized Map if you want this field to be empty.
	 * @pre (pCast != null) && (pInfo != null)
	 */
	public void setPrototypeEpisode(Map<String, String> pCast, Map<String, String> pInfo){
		assert (pCast != null) && (pInfo != null);

		// Values only for the prototype that will be updated later-on
		File aPath = new File("default path");
		String aTitle = "default title";
		int aEpisodeNum = -1;

		// Values that will will not get updated
		TVShow aShow = this;

		// Creating a prototype episode
		Episode newPrototype = new Episode(aPath, aShow, aTitle, aEpisodeNum);

		// Deep-copying all the casts
		for (Map.Entry<String, String> entry : pCast.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			newPrototype.setCast(key, value);
		}

		// Deep-copying all the info
		for (Map.Entry<String, String> entry : pInfo.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			newPrototype.setInfo(key, value);
		}

		episodePrototype = newPrototype;
	}

	/**
	 * Creates a new Episode using the prototype for this TV show, and adds it the end of the episode list.
	 * The Episode added will have the aCast and aInfo values that the prototype Episode has.
	 *
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @pre pPath != null && pTitle != null;
	 */
	public void addEpisodeFromPrototype(File pPath, String pTitle){
		assert pPath != null && pTitle != null;
		int episodeNum = aEpisodes.size()+1;

		Episode newEpisode = new Episode(episodePrototype, pPath, pTitle, episodeNum);

		aEpisodes.add(newEpisode);
	}

	/**
	 * @return clone of a TVShow.
	 * All the Episode in the TVShow are getting cloned.
	 */
	@Override
	public TVShow clone(){
		try {
			TVShow clone = (TVShow) super.clone();
			clone.aInfo = new HashMap<>();

			// copying all the info
			for (String key : this.aInfo.keySet()) {
				clone.setInfo(key, this.getInfo(key));
			}

			clone.aEpisodes = new ArrayList<>();

			// copying all the episodes
			for(Episode a : this.aEpisodes){
				clone.aEpisodes.add(a.clone());
			}

			clone.episodePrototype = (this.episodePrototype).clone();

			return clone;
		}
		catch (CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}

	// New Code Ends Here //




	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo;
	
	private List<Episode> aEpisodes = new ArrayList<>();
	private int aNextToWatch;

	/**
	 * Creates a TVShow with required metadata about the show.
	 *
	 * @param pTitle
	 *            official title of the TVShow
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	public TVShow(String pTitle, Language pLanguage, String pStudio) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aNextToWatch = 0;
		aInfo = new HashMap<>();
	}

	/**
	 * Creates a new Episode for this TV show, and adds it the end of the episode list.
	 *
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @pre pPath != null && pTitle != null;
	 */
	public void addEpisode(File pPath, String pTitle) {
		assert pPath != null && pTitle != null;
		int nb = aEpisodes.size()+1;
		Episode episode = new Episode(pPath, this, pTitle, nb);
		aEpisodes.add(episode);
	}

	@Override
	public void watch() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				episode.watch();
			}
		}
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	public Language getLanguage() {
		return aLanguage;
	}
	
	public String getStudio() {
		return aStudio;
	}
	
	public String getInfo(String pKey) {
		return aInfo.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aInfo.containsKey(pKey);
	}
	
	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aInfo.remove(pKey);
		}
		else {
			return aInfo.put(pKey, pValue);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return true if the TV show contains at least one valid episode
	 */
	@Override
	public boolean isValid() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the Episode of a given number. Episode numbers are 1-based: the first episode is 1, not 0.
	 *
	 * @param pNumber
	 *            the number whose Episode is to be returned
	 * @return the Episode of the given number
	 * @pre there is an episode for the given number
	 */
	public Episode getEpisode(int pNumber) {
		assert aEpisodes.size() >= pNumber;
		return aEpisodes.get(pNumber-1);
	}
	
	@Override
	public int getTotalCount() {
		return aEpisodes.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aEpisodes.size() - aNextToWatch;
	}
	
	@Override
	public Episode next() {
		assert getRemainingCount() > 0;
		Episode nextEpisode = aEpisodes.get(aNextToWatch);
		aNextToWatch++;
		if (aNextToWatch >= aEpisodes.size()) {
			aNextToWatch = 0;
		}
		return nextEpisode;
	}

	@Override
	public void reset() {
		aNextToWatch = 0;
	}
	
	@Override
	public Iterator<Episode> iterator() {
		return aEpisodes.iterator();
	}

	public String toString(){
		String rtn = "";
		rtn += "TVShow -> Title: " + aTitle + "\tLang: " + aLanguage + "\tStudio: "+ aStudio;

	/* Uncomment if you want to print all the episodes in the TVShow */
//		rtn += "\nEpisodes: \n";
//		for(Episode e : aEpisodes){
//			rtn += "\t"+ e.toString();
//		}

	rtn += "\n";

		return rtn;
	}

	public boolean equals(Object TVShow) {
		boolean rtn = false;
		if (this==TVShow) {
			return true;
		}
		if (TVShow==null) {
			return false;
		}
		if (getClass() != TVShow.getClass()) {
			return false;
		}
		TVShow NewTVShow = (TVShow) TVShow;
		rtn = (NewTVShow.aTitle == this.aTitle) &&
				(NewTVShow.aLanguage.equals(this.aLanguage)) &&
				(NewTVShow.aStudio == this.aStudio);
		for(Episode e : aEpisodes){
			if(!e.equals(this.getEpisode(e.getEpisodeNumber()))) return false;
		}

		return rtn;
	}

	public int hashCode() {
		return Objects.hash(aTitle, aStudio, aLanguage);
	}

}
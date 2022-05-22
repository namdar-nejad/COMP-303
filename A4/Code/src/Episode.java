import java.io.File;
import java.util.*;

/**
 * Represents a single episode, with at least a title, and an episode number. Each episode is identified by its path on
 * the file system.
 */
public class Episode implements Sequenceable<Episode>, Prototype {

	// New Code Starts Here //

	/**
	 * Episode constructor that creates an Episode based on an Episode prototype.
	 * This method should not be called by a client. Use TVShow.addProtoEpisode() instead.
	 *
	 * @param protoEpisode Episode prototype to use
	 * @param pPath Path of the new Episode
	 * @param pTitle Title of the new Episode
	 * @param pEpisodeNumber EpisodeNumber of the new Episode
	 * @pre (protoEpisode != null) && (pPath != null) && (pTitle != null)
	 * @throws IllegalArgumentException
	 * 			if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	Episode(Episode protoEpisode, File pPath, String pTitle, int pEpisodeNumber){
		assert (protoEpisode != null) && (pPath != null) && (pTitle != null);

		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}

		this.aPath = pPath;
		this.aTVShow = protoEpisode.aTVShow;
		this.aTitle = pTitle;
		this.aEpisodeNumber = pEpisodeNumber;
		this.aCast = new HashMap<>(protoEpisode.aCast);	// using the HashMap from the prototype
		this.aTags = new HashMap<>(protoEpisode.aTags); // using the HashMap from the prototype
	}

	/**
	 * clones an Episode.
	 * The TVShow of the Episode is not getting cloned.
	 * @return deep copy of an Episode
	 */
	@Override
	public Episode clone() {
		try {
			Episode clone = (Episode) super.clone();

			clone.aCast = new HashMap<>();
			clone.aTags = new HashMap<>();

			// copying all the casts
			for (String key : this.aCast.keySet()) {
				clone.setCast(key, this.getCast(key));
			}

			// copying all the info
			for (String key : this.aTags.keySet()) {
				clone.setInfo(key, this.getInfo(key));
			}

			return clone;
		}
		catch (CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}

	// New Code Ends Here //


	private final File aPath;
	private final TVShow aTVShow;
	private String aTitle;
	private int aEpisodeNumber;
	private Map<String, String> aCast = new HashMap<>();
	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Creates an episode from the file path. This method should not be called by a client. Use
	 * TVShow.addEpisode instead.
	 *
	 * @param pPath
	 *            location of the episode on the file system.
	 * @param pTVShow
	 *            TVShow that this episode is a part of
	 * @param pTitle
	 *            official title of the episode
	 * @param pEpisodeNumber
	 *            the episode number that identifies the episode
	 * @pre pPath!=null && pTVShow != null && pTitle!=null
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	Episode(File pPath, TVShow pTVShow, String pTitle, int pEpisodeNumber) {
		// Package-private constructor AND notice in the Javadoc to prevent clients from using this constructor.
		// Still, a client could create an Episode directly, and it would only affect the episode object, not the TV
		// show.
		// Alternatively, the Episode class could be nested inside TVShow, with a private constructor.
		assert (pPath != null) && (pTVShow != null) && (pTitle != null);
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTVShow = pTVShow;
		aTitle = pTitle;
		aEpisodeNumber = pEpisodeNumber;
	}

	@Override
	public void watch() {
		System.out.println("Now watching " + aTVShow.getTitle() + " [" + aEpisodeNumber + "]: " + aTitle);
	}
	
	@Override
	public boolean isValid() {
		return aPath.isFile() && aPath.canRead();
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	public String getStudio() {
		return aTVShow.getStudio();
	}
	
	public Language getLanguage() {
		return aTVShow.getLanguage();
	}
	
	/**
	 * @return the episode number of the episode
	 */
	public int getEpisodeNumber() {
		return aEpisodeNumber;
	}

	public String setCast(String pCharacter, String pActor) {
		if (pActor == null) {
			return aCast.remove(pCharacter);
		}
		else {
			return aCast.put(pCharacter, pActor);
		}
	}

	public String getCast(String pCharacter) {
		return aCast.get(pCharacter);
	}

	public Set<String> getAllCharacters() {
		return Collections.unmodifiableSet(aCast.keySet());
	}

	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aTags.remove(pKey);
		}
		else {
			return aTags.put(pKey, pValue);
		}
	}
	
	public String getInfo(String pKey) {
		return aTags.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aTags.containsKey(pKey);
	}

	public Set<String> getAllInfo() {
		return Collections.unmodifiableSet(aTags.keySet());
	}
	
	@Override
	public boolean hasPrevious() {
		return aEpisodeNumber > 1;
	}
	
	@Override
	public boolean hasNext() {
		return aEpisodeNumber < aTVShow.getTotalCount();
	}
	
	@Override
	public Episode getPrevious() {
		return aTVShow.getEpisode(aEpisodeNumber - 1);
	}
	
	@Override
	public Episode getNext() {
		return aTVShow.getEpisode(aEpisodeNumber + 1);
	}

	public String toString(){
		String rtn = "Episode -> ";
		rtn += "Num = "+aEpisodeNumber + "  Name = " + aTitle  ;
		rtn += "\n";

		/* Uncomment if you want to print all the episode's custom info */
//		// copying all the casts
//		rtn += "Cast: \n";
//		for (String key : this.aCast.keySet()) {
//			rtn += key + " -> " + this.getCast(key) + "\n";
//		}
//
//		// copying all the info
//		rtn += "Info: \n";
//		for (String key : this.aTags.keySet()) {
//			rtn += key + " -> " + this.getInfo(key) + "\n";
//		}

		return rtn;
	}

	@Override
	public boolean equals(Object Episode) {
		if (this==Episode) {
			return true;
		}
		if (Episode==null) {
			return false;
		}
		if (getClass() != Episode.getClass()) {
			return false;
		}
		Episode NewEpisode = (Episode) Episode;
		return NewEpisode.aPath.equals(((Episode) Episode).aPath)
				&& (NewEpisode.aTitle == aTitle)
				&&  (NewEpisode.aEpisodeNumber==aEpisodeNumber);
	}

	public int hashCode() {
		return Objects.hash(aTitle, aEpisodeNumber);
	}

}

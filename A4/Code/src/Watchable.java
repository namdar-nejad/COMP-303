/**
 * Represents a video object that can be watched
 */
interface Watchable {
	
	/**
	 * Plays the video to the user
	 * @pre isValid()
	 */
	public void watch();

	/**
	 * Indicates whether the Watchable element is ready to be played.
	 * 
	 * @return true if the file path points to an existing location that can be read and is not a directory
	 */
	public boolean isValid();

	/**
	 * @return Title of the Watchable object
	 */
	String getTitle();

	/**
	 * @return Language of the Watchable object
	 */
	Language getLanguage();

	/**
	 * @return Studio of the Watchable object
	 */
	String getStudio();
}
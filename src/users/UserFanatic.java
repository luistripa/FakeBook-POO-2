package users;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public interface UserFanatic extends User {

	/**
	 * Boolean method that checks if the user hates a certain topic.
	 * @param fanaticism
	 * @return true if the user hates it.
	 */
    boolean hasHateFor(String fanaticism);

    /**
	 * Boolean method that checks if the user loves a certain topic.
	 * @param fanaticism
	 * @return true if the user loves it.
	 */
    boolean hasLoveFor(String fanaticism);
}

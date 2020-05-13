package users;

import exceptions.InvalidFanaticismListException;
import hashtags.HashTag;
import hashtags.HashTagClass;

import java.util.HashMap;
import java.util.Map;

public class UserFanaticClass extends AbstractUserClass implements UserFanatic {

    private static final String LOVES = "loves";
    private static final String HATES = "hates";

    Map<String, HashTag> loves;
    Map<String, HashTag> hates;

    public UserFanaticClass(String userID, UserKind userKind) {
        super(userID, userKind);
        loves = new HashMap<>();
        hates = new HashMap<>();
    }

    @Override
    public void createNewFanaticism(String stance, String topic) throws InvalidFanaticismListException {
        if (loves.containsKey(topic) || hates.containsKey(topic))
            throw new InvalidFanaticismListException();
        if (stance.equals(LOVES))
            loves.put(topic, new HashTagClass(topic));
        else if (stance.equals(HATES))
            hates.put(topic, new HashTagClass(topic));
    }

    @Override
    public boolean hasHateFor(String fanaticism) {
        return hates.containsKey(fanaticism);
    }
}

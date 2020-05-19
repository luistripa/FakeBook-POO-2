package users;

import exceptions.InvalidFanaticismListException;
import hashtags.HashTag;
import hashtags.HashTagClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFanaticClass extends UserClass implements UserFanatic {

    private static final String LOVES = "loves";
    private static final String HATES = "hates";

    List<String> loves;
    List<String> hates;

    public UserFanaticClass(String userID, UserKind userKind, List<String> loves, List<String> hates) {
        super(userID, userKind);
        this.loves = loves;
        this.hates = hates;
    }

    @Override
    public boolean hasHateFor(String fanaticism) {
        return hates.contains(fanaticism);
    }

    @Override
    public boolean hasLoveFor(String fanaticism) {
        return loves.contains(fanaticism);
    }

}

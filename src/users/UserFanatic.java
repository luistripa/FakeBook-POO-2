package users;

import exceptions.InvalidFanaticismListException;

import java.util.List;

public interface UserFanatic extends User {

    boolean hasHateFor(String fanaticism);

    boolean isFanaticismPositive(List<String> hashtags);
}

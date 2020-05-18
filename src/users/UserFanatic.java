package users;

import exceptions.InvalidFanaticismListException;

import java.util.List;

public interface UserFanatic extends User {

    void createNewFanaticism(String stance, String topic) throws InvalidFanaticismListException;

    boolean hasHateFor(String fanaticism);

    boolean isFanaticismPositive(List<String> hashtags);
}

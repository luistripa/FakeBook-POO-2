package users;

import exceptions.InvalidFanaticismListException;

public interface UserFanatic {

    void createNewFanaticism(String stance, String topic) throws InvalidFanaticismListException;

    boolean hasHateFor(String fanaticism);
}

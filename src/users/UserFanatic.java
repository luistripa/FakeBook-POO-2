package users;

import exceptions.InvalidFanaticismListException;

public interface UserFanatic extends User {

    void createNewFanaticism(String stance, String topic) throws InvalidFanaticismListException;

    boolean hasHateFor(String fanaticism);
}

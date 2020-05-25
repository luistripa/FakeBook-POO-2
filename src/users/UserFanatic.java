package users;

public interface UserFanatic extends User {

    boolean hasHateFor(String fanaticism);

    boolean hasLoveFor(String fanaticism);
}

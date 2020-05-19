package users;

public enum UserKind {

    SELFCENTERED("selfcentered"),
    NAIVE("naive"),
    LIAR("liar"),
    FANATIC("fanatic");

    private final String userKind;

    UserKind(String userKind) {
        this.userKind = userKind;
    }

    public String getString() {
        return userKind;
    }
}

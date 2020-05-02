package fakebook;

import users.*;

public class FakeBookClass implements FakeBook {

    private UserCollection users;

    public FakeBookClass() {
        users = new UserCollectionClass();
    }
}

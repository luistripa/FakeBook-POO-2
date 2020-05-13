package hashtags;

public class HashTagClass implements HashTag {

    private final String hashtag;

    public HashTagClass(String hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public String getHashtag() {
        return hashtag;
    }
}

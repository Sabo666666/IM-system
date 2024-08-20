package JavaTest;
public class Participant {
    private String userId;
    private String displayName;

    public Participant(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}

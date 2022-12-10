package edu.utdallas.cometbooks.data.listing;

public enum BookCondition {
    NEW("New"),
    LIKE_NEW("Like-new"),
    GOOD("Good"),
    FAIR("Fair"),
    POOR("Poor"),
    ;

    private final String displayName;

    BookCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

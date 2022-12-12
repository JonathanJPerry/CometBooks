package edu.utdallas.cometbooks.data.chat;

public final class Notification {
    public static class NotificationBuilder {
        private String text;

        public NotificationBuilder text(String text) {
            this.text = text;
            return this;
        }

        public Notification build() {
            return new Notification(text);
        }
    }

    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }

    private final String text;

    private Notification(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

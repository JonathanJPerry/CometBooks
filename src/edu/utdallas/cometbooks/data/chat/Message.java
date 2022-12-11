package edu.utdallas.cometbooks.data.chat;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    public static class MessageBuilder {
        private String fromNetId;
        private String text;
        private LocalDateTime date;

        public MessageBuilder fromNetId(String from) {
            this.fromNetId = from;
            return this;
        }

        public MessageBuilder text(String text) {
            this.text = text;
            return this;
        }

        public MessageBuilder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Message build() {
            return new Message(fromNetId, text, date);
        }
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    private String fromNetId;
    private String text;
    private LocalDateTime date;

    Message(String fromNetId, String text, LocalDateTime date) {
        this.fromNetId = fromNetId;
        this.text = text;
        this.date = date;
    }

    public String getFromNetId() {
        return fromNetId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

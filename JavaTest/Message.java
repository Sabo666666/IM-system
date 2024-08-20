package JavaTest;
import java.time.LocalDateTime;

public class Message {
    private String senderId;
    private String content;
    private LocalDateTime timestamp;

    public Message(String senderId, String content, LocalDateTime timestamp) {
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

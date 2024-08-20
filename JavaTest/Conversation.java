package JavaTest;
import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private String conversationId;
    private List<Participant> participants;
    private List<Message> messages;

    public Conversation(String conversationId) {
        this.conversationId = conversationId;
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String getConversationId() {
        return conversationId;
    }
}

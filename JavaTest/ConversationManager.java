package JavaTest;
import java.util.HashMap;
import java.util.Map;

public class ConversationManager {
    private Map<String, Conversation> conversations;

    public ConversationManager() {
        conversations = new HashMap<>();
    }

    public Conversation createConversation(String conversationId) {
        Conversation conversation = new Conversation(conversationId);
        conversations.put(conversationId, conversation);
        return conversation;
    }

    public Conversation getConversation(String conversationId) {
        return conversations.get(conversationId);
    }

    public void deleteConversation(String conversationId) {
        conversations.remove(conversationId);
    }
}

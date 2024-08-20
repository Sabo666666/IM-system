package JavaTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IMServer {
    private ConversationManager conversationManager;

    public IMServer() {
        conversationManager = new ConversationManager();
    }

    public void startServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                try {
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                } finally {
                    clientSocket.close();
                }
            }
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            String response = processCommand(inputLine);
            out.println(response);
        }
    }

    private String processCommand(String command) {
        String[] tokens = command.split(" ");
        String action = tokens[0];
        String conversationId;
        String userId;
        String content;

        switch (action) {
            case "CREATE_CONVERSATION":
                conversationId = tokens[1];
                conversationManager.createConversation(conversationId);
                return "Conversation " + conversationId + " created";

            case "ADD_PARTICIPANT":
                conversationId = tokens[1];
                userId = tokens[2];
                Conversation conversation = conversationManager.getConversation(conversationId);
                if (conversation != null) {
                    conversation.addParticipant(new Participant(userId, "User " + userId));
                    return "Participant " + userId + " added to conversation " + conversationId;
                } else {
                    return "Conversation not found";
                }

            case "SEND_MESSAGE":
                conversationId = tokens[1];
                userId = tokens[2];
                content = command.substring(command.indexOf(tokens[3]));
                conversation = conversationManager.getConversation(conversationId);
                if (conversation != null) {
                    conversation.sendMessage(new Message(userId, content, java.time.LocalDateTime.now()));
                    return "Message sent to conversation " + conversationId;
                } else {
                    return "Conversation not found";
                }

            case "GET_MESSAGES":
                conversationId = tokens[1];
                conversation = conversationManager.getConversation(conversationId);
                if (conversation != null) {
                    StringBuilder messages = new StringBuilder();
                    for (Message message : conversation.getMessages()) {
                        messages.append(message.getSenderId())
                                .append(": ")
                                .append(message.getContent())
                                .append("\n");
                    }
                    return messages.toString().trim();  // Trim any trailing newlines
                } else {
                    return "Conversation not found";
                }

            case "END_CONVERSATION":
                conversationId = tokens[1];
                conversationManager.deleteConversation(conversationId);
                return "Conversation " + conversationId + " ended";

            default:
                return "Unknown command";
        }
    }

    public static void main(String[] args) throws IOException {
        IMServer server = new IMServer();
        server.startServer(12345);
    }
}

package JavaTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IMClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public IMClient(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendCommand(String command) throws IOException {
        out.println(command);
        String response = in.readLine();
        System.out.println("Server response: " + response);
    }

    public static void main(String[] args) throws IOException {
        IMClient client = new IMClient("localhost", 12345);

        // Example usage
        client.sendCommand("CREATE_CONVERSATION 12345");
        client.sendCommand("ADD_PARTICIPANT 12345 User1");
        client.sendCommand("SEND_MESSAGE 12345 User1 Hello, this is a test message.");
        client.sendCommand("GET_MESSAGES 12345");
        client.sendCommand("END_CONVERSATION 12345");
    }
}

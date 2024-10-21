package TCP;
import java.net.*;
import java.io.*;

public class EchoClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    // Method to start connection
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    // Method to send messages
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine(); // Read response from server
    }

    // Method to stop the connection
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}

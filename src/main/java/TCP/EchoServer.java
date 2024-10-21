package TCP;
import java.net.*;
import java.io.*;

public class EchoServer {
    private ServerSocket serverSocket; // Declare serverSocket
    private Socket clientSocket;        // Declare clientSocket
    private PrintWriter out;            // Declare out
    private BufferedReader in;          // Declare in

    public void start(int port) throws IOException { // Add exception handling
        serverSocket = new ServerSocket(port); // Initialize serverSocket
        clientSocket = serverSocket.accept();   // Block and wait for a client connection
        out = new PrintWriter(clientSocket.getOutputStream(), true);  // Initialize output stream
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Initialize input stream

        String inputLine;
        while ((inputLine = in.readLine()) != null) { // Read lines from client
            if (".".equals(inputLine)) { // If the client sends a dot, end the conversation
                out.println("good bye");
                break; // Exit the loop
            }
            out.println(inputLine); // Echo back the received line
        }

        stop(); // Close the connection after the loop ends
    }

    // Method to close the server and client sockets
    public void stop() throws IOException {
        in.close(); // Close the input stream
        out.close(); // Close the output stream
        clientSocket.close(); // Close the client socket
        serverSocket.close(); // Close the server socket
    }
}

package TCP;
import java.net.*;  // Networking classes
import java.io.*;   // I/O classes

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    // Method with 'throws' declaration for exceptions
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);  // Can throw IOException
        clientSocket = serverSocket.accept();   // Can throw IOException
        out = new PrintWriter(clientSocket.getOutputStream(), true);  // Can throw IOException
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Can throw IOException

        String greeting = in.readLine();  // Can throw IOException
        if ("hello server".equals(greeting)) {
            out.println("hello client");
        } else {
            out.println("unrecognised greeting");
        }
    }

    // Method with 'throws' declaration for exceptions
    public void stop() throws IOException {
        in.close();  // Can throw IOException
        out.close();  // Can throw IOException
        clientSocket.close();  // Can throw IOException
        serverSocket.close();  // Can throw IOException
    }
}

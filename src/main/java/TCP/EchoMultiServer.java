package TCP;

import java.net.*;  // Networking classes
import java.io.*;   // I/O classes

public class EchoMultiServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException { // Added IOException to method signature
        serverSocket = new ServerSocket(port);
        while (true) {
            new EchoClientHandler(serverSocket.accept()).start(); // Accepts new clients
        }
    }

    public void stop() throws IOException { // Added IOException to method signature
        serverSocket.close();
    }

    // Inner class to handle client connections
    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace in case of exceptions
            }
        }
    }
}

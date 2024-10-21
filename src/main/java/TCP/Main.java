package TCP;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Start GreetServer in a separate thread
        new Thread(() -> {
            GreetServer server = new GreetServer();
            try {
                server.start(6666);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Start EchoServer in a separate thread
        new Thread(() -> {
            EchoServer echo = new EchoServer();
            try {
                echo.start(4444);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Start EchoMultiServer in a separate thread
        new Thread(() -> {
            EchoMultiServer echoMulti = new EchoMultiServer();
            try {
                echoMulti.start(5555);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

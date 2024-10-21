package TCP;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EchoServerTest {
    private EchoClient client;

    @BeforeEach
    public void setup() {
        client = new EchoClient();
        try {
            client.startConnection("127.0.0.1", 4444); // Connect to EchoServer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            client.stopConnection(); // Close the connection
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultipleRequests() {
        try {
            String response1 = client.sendMessage("Hello Server");
            assertEquals("Hello Server", response1); // Echo back the message

            String response2 = client.sendMessage("Another message");
            assertEquals("Another message", response2); // Echo back again

            String response3 = client.sendMessage(".");
            assertEquals("good bye", response3); // Server response for termination
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

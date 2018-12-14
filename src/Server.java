import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class Server {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        System.out.println(InetAddress.getLocalHost());
        try {
            while (true) {
                Socket socket = listener.accept();
                System.out.println("listener accepted from: " + socket.getLocalPort());
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    System.out.println("initialized.");
                    while(true) {
                        try {
                            out.println("Client sending handshake...");
                            String answer = in.readLine();
                            System.out.println(answer);
                        } finally {
                            break;
                        }
                    }
                } finally {
                    socket.close();
                    break;
                }
            }
        }
        finally {
            listener.close();
        }
    }
}
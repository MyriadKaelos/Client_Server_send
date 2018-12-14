import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class Client {
    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {
//        String serverAddress = JOptionPane.showInputDialog(
//                "Enter IP Address of a machine that is\n" +
//                        "running the date service on port 9090:");
        //vv Or the host could be serverAddress vv

        try {
            Socket s = new Socket("10.137.41.74", 9090);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            System.out.println("initialized");
            while(true) {
                try {
                    String answer = in.readLine();
                    System.out.println(answer);
                    out.println("Client sending handshake...");
                } finally {
                    break;
                }
            }
        } catch(ConnectException e) {
            System.out.println(e.toString());
        }
        System.exit(0);
    }
}
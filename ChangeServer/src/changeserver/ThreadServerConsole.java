package changeserver;

import changeserver.Server;
import changeserver.ClientHandler;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import javax.swing.*;


public class ThreadServerConsole extends JFrame {
    
    private JTextArea jTextArea = new JTextArea();
    private Server server;

    public static void main(String[] args) throws IOException {
        new ThreadServerConsole();
    }

    public ThreadServerConsole() throws IOException {
        Date dt = new Date();  // current time
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        Font font = new Font("Ink Free", Font.BOLD, 20);//Showcard GothicCooper Black
        jTextArea.setFont(font);
        jTextArea.setForeground(Color.BLUE);
        server = new Server();
        jTextArea.setSize(100,499);
        // Place text area on the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(jTextArea), BorderLayout.CENTER);
        setTitle("                          console server thread                                                             ");
        setSize(1370, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!Segoe Script

        jTextArea.append("                                                                 the server thread starts now, the time is: " + hours + ":" + minutes + '\n');

        int clientNo = 1;

        while (true) {
            // Listen for a new connection request
            Socket socket = server.Accept();

            // Display the client number
            jTextArea.append("                                                                  the client " + clientNo + " at " + hours + ":" + minutes + " begins his thread" + '\n');

            // Find the client's host name, and IP address
            InetAddress inetAddress = socket.getInetAddress();
            jTextArea.append("                                                                 the host name OF CLIENT " + clientNo + " is " + inetAddress.getHostName() + "\n");
            jTextArea.append("                                                                  the IP ADDRESS OF CLIENT " + clientNo + " is " + inetAddress.getHostAddress() + "\n");

            // Create a new task for the connection
            Thread task = new Thread(new ClientHandler(socket));
            task.start();
            clientNo++;
        }
    }
}

package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
    private ServerSocket serverSocket;


    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while(true)
            new ServerHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    public static void main(String args[]) throws IOException
    {
        ServerSocket listener = null;
        System.out.println("Server is waiting to accept user...");

        try {
            listener = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                Socket socketOfServer = listener.accept();
                ServerHandler client = new  ServerHandler(socketOfServer);
                client.start();
                System.out.println("Connection...");
            }
        } finally {
            listener.close();
        }

    }



    private static class ServerHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        List<String> all_mess = new ArrayList<>();

        public ServerHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String inputLine  = null;
            try {
                inputLine = in.readLine();
                System.out.println("Server receive: " + inputLine);
                all_mess.add(inputLine);
                out.println(inputLine);
                System.out.println("Server send: " + inputLine);


            } catch (IOException e) {
                e.printStackTrace();
            }
            while (inputLine != null)
            {
                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }
                try {
                    inputLine = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println(inputLine);

            }

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package Socket;

import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main extends Thread {


    public static void main(String[] args) throws IOException {
        new LogIn("127.0.0.1", "6666");
        new LogIn("127.0.0.2", "6666");
    }
}



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AndroidServer implements Runnable {
    public static final int port = 7777;
    public static final String ip = "127.0.0.1";

    @Override
    public void run() {
        try {
            System.out.println("Connecting...");
            ServerSocket serverSocket = new ServerSocket(port);

            while(true) {
                Socket client = serverSocket.accept();
                System.out.println("MSG receiving...");
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String str = in.readLine();
                    System.out.println(str);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    client.close();
                    System.out.println("Complete");
                }
            } //while
        } catch (Exception e) {
            System.out.println("error.");
            e.printStackTrace();
        }
    } //run() override

    public static void main(String[] args) {
        Thread desktopServerThread = new Thread(new AndroidServer());
        desktopServerThread.start();
    } //main
} //class

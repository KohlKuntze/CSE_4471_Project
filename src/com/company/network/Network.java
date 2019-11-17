package com.company.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Network {

    private static final String ARP_GET_IP_HW = "arp -a";

    public static String getARPTable(String cmd) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process commandLineOutput = runtime.exec(cmd);
        InputStream inputStream = commandLineOutput.getInputStream();
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static List<NetworkDevice> getDevices() throws IOException {
        String table = getARPTable(ARP_GET_IP_HW );
        System.out.println(table);

        List<NetworkDevice> devices;

        String operatingSystemName = System.getProperty("os.name");

        if (operatingSystemName.equals("Mac OS X")) {
            devices = getNetworkDevicesMac(table);
        } else {
            devices = getNetworkDevicesWindows(table);
        }

        return devices;
    }

    private static List<NetworkDevice> getNetworkDevicesWindows(String table) {
        String[] arr = table.split(" ");
        List<String> list = Arrays.asList(arr);
        List<String> nums = new ArrayList<>();
        List<NetworkDevice> devices = new ArrayList<>();

        int c = 0;
        while(c < list.size()){
            if(list.get(c).matches(".*\\d.*")){
                nums.add(list.get(c));
            }
            c++;
        }

        for(int i = 0; i<nums.size(); i++){
            if(nums.get(i).contains("192.168")){
                if(nums.get(i+1).contains("-")){
                    NetworkDevice cpu = new NetworkDevice(nums.get(i), nums.get((i+1)));
                    devices.add(cpu);
                }
            }
        }
        return devices;
    }

    private static List<NetworkDevice> getNetworkDevicesMac(String table) {
        String[] arr = table.split(" ");
        List<String> list = Arrays.asList(arr);
        List<String> ipAddresses = new ArrayList<>();
        List<String> macAddresses = new ArrayList<>();

        List<NetworkDevice> devices = new ArrayList<>();

        for (String str : list) {

            if (isMacIpAddress(str)) {
                String ipAddress = str.substring(1, str.length() - 1);
                ipAddresses.add(ipAddress);
            }

            if (isMacMacAddress(str) || str.equals("(incomplete)")) {
                macAddresses.add(str);
            }
        }

        for (int i = 0; i < ipAddresses.size(); i++) {
            String currentIp = ipAddresses.get(i);
            String currentMac = macAddresses.get(i);

            NetworkDevice networkDevice = new NetworkDevice(currentIp, currentMac);

            devices.add(networkDevice);
        }

        return devices;
    }

    private static boolean isMacIpAddress(String str) {
        if (str.indexOf(".") > 0) {
            return true;
        }

        return false;
    }

    private static boolean isMacMacAddress(String str) {
        if (str.indexOf(":") > 0) {
            return true;
        }

        return false;
    }

    public static String getNameCommand(String cmd) throws IOException {
        Scanner s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String getName(String ip) throws IOException {
        String table = getNameCommand("ping -a "+ip);
        String[] arr = table.split(" ");
        return arr[1];

    }
}

package com.company.network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class NameListener implements ActionListener
{

    JFrame jFrame;
    String ip;
    String mac;

    public NameListener(JFrame jFrame, String ip, String mac){
        this.ip = ip;
        this.mac = mac;
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent event)
    {
        // TODO
        String name = "unable to find name";
        try {
            name = getName();

        } catch (IOException e) {
            e.printStackTrace();
        }
        jFrame.dispose();

        try {
            AcceptRejectWithName.AcceptOrReject(mac, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNameCommand(String cmd) throws IOException {
        Scanner s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String getName() throws IOException {
        String table = getNameCommand("ping -a "+ip);
        String[] arr = table.split(" ");
        return arr[1];
    }
}
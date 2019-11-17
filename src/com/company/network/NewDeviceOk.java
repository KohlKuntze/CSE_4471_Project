package com.company.network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class NewDeviceOk
{
    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 125;

    public static void newDeviceFound(String MACAddress, String name) throws IOException {
        JFrame frame = new JFrame("New MAC Address found");
        JPanel panel = new JPanel();

        JLabel label = new JLabel();
        label.setText("New MAC Address found: "+MACAddress+"\t\t\t\n");
        panel.add(label);

        JLabel nameOfDevice = new JLabel();
        nameOfDevice.setText("Name of device: "+name+"\t\t\t");
        panel.add(nameOfDevice);

        JLabel spacer = new JLabel();
        spacer.setText("          ");
        panel.add(spacer);

        JButton okButton = new JButton("\tOk\t");
        panel.add(okButton);
        frame.add(panel);


        ActionListener okListener = new okListener(frame);
        okButton.addActionListener(okListener);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
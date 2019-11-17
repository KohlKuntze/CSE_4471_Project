package com.company.network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class AcceptRejectButtons
{
    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 100;

    public static void AcceptOrReject(String MACAddress, String ip) throws IOException {
        JFrame frame = new JFrame("New MAC Address found");
        JPanel panel = new JPanel();

        JLabel label = new JLabel();
        label.setText("New MAC Address found: "+MACAddress+"\t\t\t\n");
        panel.add(label);

        JButton acceptButton = new JButton("\tAccept\t");
        panel.add(acceptButton);
        JButton rejectButton = new JButton("\tReject\t");
        panel.add(rejectButton);
        JButton nameButton = new JButton("\tRetrieve name\t");
        panel.add(nameButton);
        frame.add(panel);


        ActionListener acceptListen = new AcceptListener(frame, MACAddress);
        acceptButton.addActionListener(acceptListen);
        ActionListener rejectListen = new RejectListener(frame, MACAddress);
        rejectButton.addActionListener(rejectListen);
        ActionListener nameListener = new NameListener(frame, ip, MACAddress);
        nameButton.addActionListener(nameListener);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
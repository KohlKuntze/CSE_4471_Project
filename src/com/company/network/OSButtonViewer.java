package com.company.network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OSButtonViewer
{
    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 100;

    public static void getComputer()
    {
        JFrame frame = new JFrame("Select your CPU type");
        JPanel panel = new JPanel();

        JButton macButton = new JButton("\tMac/Linux\t");
        panel.add(macButton);
        JButton windowsButton = new JButton("\tWindows\t");
        panel.add(windowsButton);
        frame.add(panel);

        ActionListener macListen = new MacClickListener(frame);
        macButton.addActionListener(macListen);
        ActionListener windowsListen = new WindowsClickListener(frame);
        windowsButton.addActionListener(windowsListen);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
package com.company.network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RejectListener implements ActionListener
{

    JFrame jFrame;
    String mac;

    public RejectListener(JFrame jFrame, String mac){
        this.mac = mac;
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent event)
    {
        // TODO
        jFrame.dispose();
    }
}
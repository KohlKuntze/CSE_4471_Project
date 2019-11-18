package com.company.network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class okListener implements ActionListener {

    JFrame jFrame;

    public okListener(JFrame jFrame){
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent event)
    {
        jFrame.dispose();
    }
}

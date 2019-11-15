package com.company.network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WindowsClickListener implements ActionListener
{

    JFrame jFrame;

    public WindowsClickListener(JFrame jFrame){
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent event)
    {
        try {
            Main.windows();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jFrame.dispose();
    }
}
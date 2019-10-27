package com.company.ui.view;

import javax.swing.*;
import java.awt.*;

public class ProjectView{

    JFrame frame;

    public ProjectView(String programName) {
        this.frame = new JFrame(programName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel getTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        return topPanel;
    }
    public static ProjectView getInstance() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ProjectView newView = new ProjectView("View Test");
        return newView;
    }
}

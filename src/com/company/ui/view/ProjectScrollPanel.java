package com.company.ui.view;

import com.company.ui.controller.ProjectActionListeners.PermissionActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ProjectScrollPanel extends JPanel {

    DefaultListModel model;
    final JButton actionButton;
    final JList macAddressJList;
    JScrollPane scrollPane;
    SelectedItemListener selectedItemListener;

    public ProjectScrollPanel(PermissionActionListener actionListener, String label) {
        setLayout(new BorderLayout());

        actionButton = getActionButton(label, actionListener);
        add(actionButton, BorderLayout.NORTH);

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            list.add(UUID.randomUUID().toString());
        }

        model = createMacAddressListModel(list);
        //model = createMacAddressListModel(new ArrayList<>());

        macAddressJList = new JList();
        macAddressJList.setModel(model);

        selectedItemListener = new SelectedItemListener();
        macAddressJList.addListSelectionListener(selectedItemListener);

        scrollPane = getScrollPane(macAddressJList);
        add(scrollPane);

        actionListener.setScrollPanel(this);

        setVisible(true);
    }

    public DefaultListModel getModel() {
        return model;
    }

    public JList getMacAddressJList() {
        return macAddressJList;
    }

    public List<String> getMacAddressList() {
        List<String> macAddressList = new ArrayList<>();

        for (int i = 0; i < macAddressJList.getModel().getSize(); i++) {

            macAddressList.add((String) macAddressJList.getModel().getElementAt(i));

        }

        return macAddressList;
    }

    public void update(List<String> updatedMacAddresses) {
        model = createMacAddressListModel(updatedMacAddresses);
        macAddressJList.setModel(model);
    }

    public int getSelectedItemIndex() {
        return selectedItemListener.selectedItemIndex;
    }

    private static JScrollPane getScrollPane(JList macAddressJList) {
        JScrollPane scrollPane = new JScrollPane(macAddressJList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(750, 500);

        return scrollPane;
    }

    private static JButton getActionButton(String label, PermissionActionListener actionListener) {
        JButton actionButton = new JButton(label);

        actionButton.addActionListener(actionListener);
        actionButton.setSize(750, 50);
        actionButton.setVisible(true);

        return actionButton;
    }

    private static DefaultListModel createMacAddressListModel(List<String> macAddresses) {
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < macAddresses.size(); i++) {
            model.addElement(macAddresses.get(i));
        }

        return model;
    }

    private class SelectedItemListener implements ListSelectionListener {

        Integer selectedItemIndex;

        public SelectedItemListener() {
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                if (selectedItemIndex == null) {
                    selectedItemIndex = e.getLastIndex();
                } else {
                    if (selectedItemIndex == e.getFirstIndex()) {
                        selectedItemIndex = e.getLastIndex();
                    } else {
                        selectedItemIndex = e.getFirstIndex();
                    }
                }
            }
        }


    }
}

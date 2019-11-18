package com.company.app;

import com.company.DDosObject;
import com.company.DataBase.SQLiteDB;
import com.company.network.Network;
import com.company.network.NetworkDevice;
import com.company.ui.controller.ProjectController;
import com.company.ui.model.ProjectModel;
import com.company.ui.utilities.ScrollPanelUtilties;
import com.company.ui.view.ProjectScrollPanel;
import com.company.ui.view.ProjectView;

import java.io.IOException;
import java.util.List;

public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException {
        SQLiteDB.createTable();
        SQLiteDB.deleteSeenTable();
        SQLiteDB.createSeenTable();

        ProjectController controller = getController();
        run(controller);

    }

    private static void run(ProjectController controller) throws IOException, InterruptedException {
        ScrollPanelUtilties.initalStartUp(controller.getProjectView());
        Thread.sleep(1000);


        while (true) {
            List<NetworkDevice> allDevices = Network.getDevices();
            ScrollPanelUtilties.updateView(controller.getProjectView());

            ProjectScrollPanel unknownIpAddressesPanel = controller.getProjectView().getUnknownIpAddressesPanel();

            List<NetworkDevice> devices = ScrollPanelUtilties.getUnknownDevices(allDevices);

            for(NetworkDevice device: devices){
                //Create and start 100 threads
                DDosObject foreignDevice = new DDosObject(device.getIP());
                for (int i = 0; i < 5; i++) {
                    DDosObject.DdosThread thread = new DDosObject.DdosThread(foreignDevice);
                    thread.start();
                }
            }

            Thread.sleep(5000);

        }
    }


    private static ProjectController getController()
    {
        ProjectModel model = new ProjectModel("Name");
        ProjectView view = new ProjectView();
        ProjectController controller = new ProjectController(model, view);

        return controller;
    }
}

package com.company.app;

import com.company.DataBase.SQLiteDB;
import com.company.ui.controller.ProjectController;
import com.company.ui.model.ProjectModel;
import com.company.ui.utilities.ScrollPanelUtilties;
import com.company.ui.view.ProjectView;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException {
        SQLiteDB.createTable();

        ProjectController controller = getController();
        run(controller);

    }

    private static void run(ProjectController controller) throws IOException, InterruptedException {
        ScrollPanelUtilties.initalStartUp(controller.getProjectView());
        Thread.sleep(5000);
        Thread.sleep(10000);


        while (true) {
            ScrollPanelUtilties.updateView(controller.getProjectView());
            Thread.sleep(5000);
            Thread.sleep(10000);

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

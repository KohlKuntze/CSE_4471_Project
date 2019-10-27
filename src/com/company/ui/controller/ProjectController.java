package com.company.ui.controller;

import com.company.ui.model.ProjectModel;
import com.company.ui.view.ProjectView;

public class ProjectController {

    private ProjectModel model;
    private ProjectView view;

    public ProjectController(ProjectModel model, ProjectView view) {
        this.model = model;
        this.view = view;
    }

}

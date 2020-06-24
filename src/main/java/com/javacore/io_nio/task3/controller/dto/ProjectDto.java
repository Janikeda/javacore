package main.java.com.javacore.io_nio.task3.controller.dto;

import java.util.Set;
import main.java.com.javacore.io_nio.task3.model.ProjectStatus;

public class ProjectDto extends BaseDto  {

    private Set<DeveloperDto> developers;
    private ProjectStatus projectStatus;

    public Set<DeveloperDto> getDevelopers() {
        return developers;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setDevelopers(
        Set<DeveloperDto> developers) {
        this.developers = developers;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "Project " +
            "ID = "+ getId() +
            ", projectStatus = " + projectStatus +
            ", developers = " + developers;
    }
}

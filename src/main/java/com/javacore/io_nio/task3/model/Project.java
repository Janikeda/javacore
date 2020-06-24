package main.java.com.javacore.io_nio.task3.model;

import java.util.Set;

public class Project extends BaseEntity {

    private Set<Developer> developers;

    private ProjectStatus projectStatus;

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}

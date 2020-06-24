package main.java.com.javacore.io_nio.task3.controller;

import java.util.HashSet;
import java.util.Set;
import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.Account;
import main.java.com.javacore.io_nio.task3.model.Developer;
import main.java.com.javacore.io_nio.task3.model.Project;

public final class DtoMapper {

    static Project fromProjectDtoToEntity(ProjectDto projectDto) {
        Project project = new Project();
        Set<Developer> developers = new HashSet<>();

        projectDto.getDevelopers().forEach(developerDto -> {
            Developer developer = new Developer();
            developer.setAccount(fromAccDtoToEntity(developerDto.getAccount()));
            developers.add(developer);
        });

        project.setProjectStatus(projectDto.getProjectStatus());
        project.setDevelopers(developers);
        if (projectDto.getId() != null) {
            project.setId(projectDto.getId());
        }

        return project;
    }

    private static Account fromAccDtoToEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setData(accountDto.getData());
        return account;
    }


}

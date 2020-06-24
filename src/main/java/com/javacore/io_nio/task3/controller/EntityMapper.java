package main.java.com.javacore.io_nio.task3.controller;

import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.DeveloperDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.Account;
import main.java.com.javacore.io_nio.task3.model.Developer;
import main.java.com.javacore.io_nio.task3.model.Project;

public final class EntityMapper {

    public static ProjectDto fromProjectEntityToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectStatus(project.getProjectStatus());
        return projectDto;
    }

    public static DeveloperDto fromDeveloperEntityToDto(Developer developer) {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(developer.getId());
        developerDto.setProjectId(developer.getProjectId());
        return developerDto;
    }

    public static AccountDto fromAccountEntityToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setData(account.getData());
        accountDto.setDevId(account.getDevId());
        return accountDto;
    }
}

package main.java.com.javacore.io_nio.task3.controller;

import static main.java.com.javacore.io_nio.task3.controller.EntityMapper.fromAccountEntityToDto;
import static main.java.com.javacore.io_nio.task3.controller.EntityMapper.fromProjectEntityToDto;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.printDeleteMessage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.DeveloperDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.Account;
import main.java.com.javacore.io_nio.task3.model.BaseEntity;
import main.java.com.javacore.io_nio.task3.model.Developer;
import main.java.com.javacore.io_nio.task3.model.Project;
import main.java.com.javacore.io_nio.task3.repository.AccountRepository;
import main.java.com.javacore.io_nio.task3.repository.DeveloperRepository;
import main.java.com.javacore.io_nio.task3.repository.ProjectRepository;
import main.java.com.javacore.io_nio.task3.repository.impl.AccountRepositoryImpl;
import main.java.com.javacore.io_nio.task3.repository.impl.DeveloperRepositoryImpl;
import main.java.com.javacore.io_nio.task3.repository.impl.ProjectRepositoryImpl;


public class ProjectController {

    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final AccountRepository accountRepository;

    public ProjectController() {
        projectRepository = new ProjectRepositoryImpl();
        developerRepository = new DeveloperRepositoryImpl();
        accountRepository = new AccountRepositoryImpl();
    }

    public boolean save(ProjectDto projectDto) throws IOException {
        Project project = projectRepository.save(DtoMapper.fromProjectDtoToEntity(projectDto));
        Set<Developer> developers = developerRepository
            .saveAll(project.getId(), project.getDevelopers());
        Set<Account> accounts = accountRepository.saveAll(developers);
        return true;
    }

    public boolean read(Integer id) throws IOException {
        if (id == null) {
            List<ProjectDto> projects = projectRepository.findAll().stream()
                .map(EntityMapper::fromProjectEntityToDto).collect(Collectors.toList());
            prepareProjectResponse(projects);
            System.out.println(projects);
        } else {
            Project entity = projectRepository.findById(id);
            if (entity != null) {
                ProjectDto project = fromProjectEntityToDto(entity);
                prepareProjectResponse(Collections.singletonList(project));
                System.out.println(project);
            }
        }
        return true;
    }

    public boolean delete(Integer id) throws IOException {
        if (id == null) {
            projectRepository.deleteAll();
            developerRepository.deleteAll();
            accountRepository.deleteAll();
        } else {
            int projects = projectRepository.deleteById(Collections.singletonList(id));
            List<Integer> devIds = developerRepository.findAllByProject(id).stream()
                .map(BaseEntity::getId).collect(
                    Collectors.toList());
            int devs = developerRepository.deleteById(devIds);
            List<Integer> accIds = accountRepository.findByDeveloper(devIds).stream()
                .map(BaseEntity::getId).collect(
                    Collectors.toList());
            int accs = accountRepository.deleteById(accIds);
            System.out.println(printDeleteMessage(projects, devs, accs));
        }
        return true;
    }

    public boolean update(ProjectDto projectDto) throws IOException {
        if (projectDto.getId() == null) {
            return false;
        } else {
            projectRepository.deleteById(Collections.singletonList(projectDto.getId()));
            projectRepository.save(DtoMapper.fromProjectDtoToEntity(projectDto));
            return true;
        }
    }


    private void prepareProjectResponse(List<ProjectDto> projects) throws IOException {
        for (ProjectDto projectDto : projects) {
            Set<DeveloperDto> developers = developerRepository
                .findAllByProject(projectDto.getId()).stream()
                .map(EntityMapper::fromDeveloperEntityToDto).collect(Collectors.toSet());
            for (DeveloperDto developerDto : developers) {
                AccountDto account = fromAccountEntityToDto(
                    accountRepository.findByDeveloper(
                        Collections.singletonList(developerDto.getId())).get(0));
                developerDto.setAccount(account);
            }
            projectDto.setDevelopers(developers);
        }
    }

}

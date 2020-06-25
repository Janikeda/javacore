package main.java.com.javacore.io_nio.task3.controller;

import static main.java.com.javacore.io_nio.task3.controller.EntityMapper.fromProjectEntityToDto;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.printDeleteMessage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import main.java.com.javacore.io_nio.task3.controller.dto.BaseDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.BaseEntity;
import main.java.com.javacore.io_nio.task3.model.Project;


public class ProjectController extends CommonController {

    public List<? extends BaseDto> read(Integer id) throws IOException {
        if (id == null) {
            return readAll();
        } else {
            Project entity = projectRepository.findById(id);
            ProjectDto projectDto;
            if (entity != null) {
                projectDto = fromProjectEntityToDto(entity);
                prepareProjectResponse(Collections.singletonList(projectDto));
            } else {
                projectDto = new ProjectDto();
            }
            return Collections.singletonList(projectDto);
        }
    }

    public boolean create(ProjectDto projectDto) throws IOException {
        return commonCreate(projectDto);
    }

    public String delete(Integer id) throws IOException {
        if (id == null) {
            deleteAll();
            return "OK";
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
            return printDeleteMessage(projects, devs, accs);
        }
    }

    public boolean update(ProjectDto projectDto) throws IOException {
        if (projectDto.getId() == null) {
            return false;
        } else {
            int count = projectRepository.deleteById(Collections.singletonList(projectDto.getId()));
            if (count == 0) {
                return false;
            }
            projectRepository.save(DtoMapper.fromProjectDtoToEntity(projectDto));
            return true;
        }
    }

}

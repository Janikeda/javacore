package main.java.com.javacore.io_nio.task3.controller;

import static main.java.com.javacore.io_nio.task3.controller.EntityMapper.fromDeveloperEntityToDto;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.printDeleteMessage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import main.java.com.javacore.io_nio.task3.controller.dto.BaseDto;
import main.java.com.javacore.io_nio.task3.controller.dto.DeveloperDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.BaseEntity;
import main.java.com.javacore.io_nio.task3.model.Developer;

public class DeveloperController extends CommonController {


    public List<? extends BaseDto> read(Integer id) throws IOException {
        if (id == null) {
            return readAll();
        } else {
            Developer entity = developerRepository.findById(id);
            DeveloperDto developerDto;
            if (entity != null) {
                developerDto = fromDeveloperEntityToDto(entity);
                prepareDeveloperResponse(developerDto);
            } else {
                developerDto = new DeveloperDto();
            }
            return Collections.singletonList(developerDto);
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
            Developer developer = developerRepository.findById(id);
            int devs = developerRepository.deleteById(Collections.singletonList(developer.getId()));
            List<Integer> accIds = accountRepository
                .findByDeveloper(Collections.singletonList(developer.getId())).stream()
                .map(BaseEntity::getId).collect(
                    Collectors.toList());
            int accs = accountRepository.deleteById(accIds);
            return (printDeleteMessage(0, devs, accs));
        }
    }

    public boolean update(DeveloperDto developerDto) throws IOException {
        if (developerDto.getId() == null) {
            return false;
        } else {
            int count = developerRepository
                .deleteById(Collections.singletonList(developerDto.getId()));
            if (count == 0) {
                return false;
            }
            developerRepository.save(DtoMapper.fromDeveloperDtoToEntity(developerDto));
            return true;
        }
    }
}

package main.java.com.javacore.io_nio.task3.repository.impl;

import static main.java.com.javacore.io_nio.task3.utils.Constants.DELIMITER;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.calculateCurrentId;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findLineById;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import main.java.com.javacore.io_nio.task3.model.Project;
import main.java.com.javacore.io_nio.task3.model.ProjectStatus;
import main.java.com.javacore.io_nio.task3.repository.ProjectRepository;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final Path pathToDb;
    private Integer currentId;
    private final String DATABASE = "projects.csv";

    public ProjectRepositoryImpl() {
        this.pathToDb = Paths.get(DATABASE);
        this.currentId = calculateCurrentId(this.pathToDb);
    }

    @Override
    public Project findById(Integer id) throws IOException {
        String line = findLineById(id, pathToDb);
        return parseDbRow(line);
    }

    @Override
    public List<Project> findAll() throws IOException {
        return findAllCommon(pathToDb, this::parseDbRow);
    }

    @Override
    public Project save(Project entity) throws IOException {
        Integer id;
        if (entity.getId() == null) {
            id = ++currentId;
            entity.setId(id);
        } else {
            id = entity.getId();
        }
        writeToDb(pathToDb, id + DELIMITER + entity.getProjectStatus());

        return entity;
    }

    @Override
    public void deleteAll() throws IOException {
        deleteAllCommon(pathToDb);
    }

    @Override
    public int deleteById(List<Integer> ids) throws IOException {
        return deleteByIdCommon(ids, pathToDb);
    }

    /*
     * Метод-парсер. Из записи в БД в java объект
     * */
    private Project parseDbRow(String row) {
        if (row == null) {
            return null;
        }
        List<String> columns = Arrays.asList(row.split(DELIMITER));
        Project project = new Project();
        project.setId(Integer.parseInt(columns.get(0)));
        project.setProjectStatus(ProjectStatus.valueOf(columns.get(1)));
        return project;
    }

}

package main.java.com.javacore.io_nio.task3.repository.impl;

import static main.java.com.javacore.io_nio.task3.utils.Constants.DELIMITER;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.calculateCurrentId;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findAllLines;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findLineById;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import main.java.com.javacore.io_nio.task3.model.Developer;
import main.java.com.javacore.io_nio.task3.repository.DeveloperRepository;

public class DeveloperRepositoryImpl implements DeveloperRepository {

    private final Path pathToDb;
    private Integer currentId;
    private final String DATABASE = "developers.csv";


    public DeveloperRepositoryImpl() {
        this.pathToDb = Paths.get(DATABASE);
        this.currentId = calculateCurrentId(this.pathToDb);
    }

    @Override
    public Developer findById(Integer id) throws IOException {
        return parseDbRow(findLineById(id, pathToDb));
    }

    @Override
    public List<Developer> findAll() throws IOException {
        return findAllCommon(pathToDb, this::parseDbRow);
    }

    @Override
    public Developer save(Developer entity) throws IOException {
        Integer id;
        if (entity.getId() == null) {
            id = ++currentId;
            entity.setId(id);
        } else {
            id = entity.getId();
        }
        writeToDb(pathToDb, id + DELIMITER + entity.getProjectId());

        return entity;
    }

    @Override
    public Set<Developer> saveAll(Integer projectId, Set<Developer> entities) throws IOException {
        for (Developer developer : entities) {
            Integer id = ++currentId;
            writeToDb(pathToDb, id + DELIMITER + projectId);
            developer.setId(id);

        }
        return entities;
    }

    @Override
    public Set<Developer> findAllByProject(Integer projectId) throws IOException {
        List<Developer> developers = findAllLines(pathToDb).stream().map(this::parseDbRow)
            .collect(Collectors.toList());

        return developers.stream().filter(dev -> dev.getProjectId().equals(projectId))
            .collect(
                Collectors.toSet());
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
    private Developer parseDbRow(String row) {
        if (row == null) {
            return null;
        }
        List<String> columns = Arrays.asList(row.split(DELIMITER));
        Developer developer = new Developer();
        developer.setId(Integer.parseInt(columns.get(0)));
        developer.setProjectId(Integer.parseInt(columns.get(1)));
        return developer;
    }
}

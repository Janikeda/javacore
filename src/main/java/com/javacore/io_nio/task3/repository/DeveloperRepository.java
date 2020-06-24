package main.java.com.javacore.io_nio.task3.repository;

import java.io.IOException;
import java.util.Set;
import main.java.com.javacore.io_nio.task3.model.Developer;

public interface DeveloperRepository extends GenericRepository<Developer, Integer> {

    Set<Developer> saveAll(Integer projectId, Set<Developer> entities) throws IOException;

    Set<Developer> findAllByProject(Integer projectId) throws IOException;

}

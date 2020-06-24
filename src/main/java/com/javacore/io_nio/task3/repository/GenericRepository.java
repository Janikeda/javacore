package main.java.com.javacore.io_nio.task3.repository;

import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findLinesByIds;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import main.java.com.javacore.io_nio.task3.model.BaseEntity;

public interface GenericRepository<T extends BaseEntity, ID> {

    T findById(ID id) throws IOException;

    List<T> findAll() throws IOException;

    T save(T entity) throws IOException;

    void deleteAll() throws IOException;

    int deleteById(List<Integer> ids) throws IOException;

    default void writeToDb(Path pathToDb, String value) throws IOException {
        Files.write(pathToDb, Collections.singleton(value), StandardOpenOption.APPEND);
    }

    default List<T> findAllCommon(Path pathToDb, Function<String, T> function) throws IOException {
        List<String> lines = Files.readAllLines(pathToDb, StandardCharsets.UTF_8);
        return lines.stream().map(function).collect(Collectors.toList());
    }

    default void deleteAllCommon(Path pathToDb) throws IOException {
        Files.newBufferedWriter(pathToDb).close();
    }

    default int deleteByIdCommon(List<Integer> ids, Path pathToDb) throws IOException {
        List<String> linesToDelete = findLinesByIds(ids, pathToDb);
        List<String> out = Files.lines(pathToDb)
            .filter(line -> !linesToDelete.contains(line))
            .collect(Collectors.toList());
        Files.write(pathToDb, out, StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING);
        return linesToDelete.size();
    }
}

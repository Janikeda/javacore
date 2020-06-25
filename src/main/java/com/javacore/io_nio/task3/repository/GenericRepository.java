package main.java.com.javacore.io_nio.task3.repository;

import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findAllLines;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findLinesByIds;

import java.io.IOException;
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

    /*
     * Берем текущий ID, увеличиваем на 1 и сохраняем в БД
     * */
    T save(T entity) throws IOException;

    void deleteAll() throws IOException;

    int deleteById(List<Integer> ids) throws IOException;

    /*
     * Запись строки в БД
     * */
    default void writeToDb(Path pathToDb, String value) throws IOException {
        Files.write(pathToDb, Collections.singleton(value), StandardOpenOption.APPEND);
    }

    /*
     * Получить все записи из БД и смаппить их в POJO
     * */
    default List<T> findAllCommon(Path pathToDb, Function<String, T> function) throws IOException {
        return findAllLines(pathToDb).stream().map(function).collect(Collectors.toList());
    }

    /*
     * Удалить все записи из БД
     * */
    default void deleteAllCommon(Path pathToDb) throws IOException {
        Files.newBufferedWriter(pathToDb).close();
    }

    /*
     * Удалить все записи из БД, где ID есть в ids     *
     * */
    default int deleteByIdCommon(List<Integer> ids, Path pathToDb) throws IOException {
        List<String> linesToDelete = findLinesByIds(ids, pathToDb);
        if (linesToDelete.isEmpty()) {
            return 0;
        } else {
            List<String> out = Files.lines(pathToDb)
                .filter(line -> !linesToDelete.contains(line))
                .collect(Collectors.toList());
            Files.write(pathToDb, out, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
            return linesToDelete.size();
        }
    }
}

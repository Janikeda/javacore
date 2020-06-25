package main.java.com.javacore.io_nio.task3.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public final class DbUtils {

    /*
     * Получить одну запись из БД по ID
     * */
    public static String findLineById(Integer id, Path pathToDb) throws IOException {
        return findAllLines(pathToDb).stream()
            .filter(str -> {
                Integer lineId = parseStringGetId(str);
                return id.equals(lineId);
            })
            .findFirst().orElseGet(() -> notFoundLine(id));
    }

    /*
     * Получить все записи из БД по нескольким ID
     * */
    public static List<String> findLinesByIds(List<Integer> ids, Path pathToDb) throws IOException {
        return findAllLines(pathToDb).stream()
            .filter(str -> {
                Integer id = parseStringGetId(str);
                return ids.contains(id);
            }).collect(Collectors.toList());
    }

    /*
     * При запуске приложения рассчитывается последний ID в БД для генерации новых
     * */
    public static Integer calculateCurrentId(Path pathToDb) {
        int currId;
        try {
            currId = findAllLines(pathToDb).stream().map(DbUtils::parseStringGetId).mapToInt(v -> v)
                .max()
                .orElse(0);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return currId;
    }

    /*
     * Получить все записи из БД
     * */
    public static List<String> findAllLines(Path pathToDb) throws IOException {
        return Files.readAllLines(pathToDb, StandardCharsets.UTF_8);
    }

    private static String notFoundLine(Integer id) {
        System.out.println("С данным ID: " + id.toString() + " не найдена сущность!");
        return null;
    }

    /*
     * Получить ID у записи из БД
     * */
    private static Integer parseStringGetId(String str) {
        return Integer.parseInt(str.split(",")[0]);
    }

}

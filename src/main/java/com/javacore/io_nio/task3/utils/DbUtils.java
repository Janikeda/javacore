package main.java.com.javacore.io_nio.task3.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public final class DbUtils {


    public static String findLineById(Integer id, Path pathToDb) throws IOException {
        List<String> lines = Files.readAllLines(pathToDb, StandardCharsets.UTF_8);

        return lines.stream()
            .filter(str -> str.contains(id.toString()))
            .findFirst().orElseGet(() -> notFoundLine(id));
    }

    public static List<String> findLinesByIds(List<Integer> ids, Path pathToDb) throws IOException {
        List<String> lines = Files.readAllLines(pathToDb, StandardCharsets.UTF_8);
        return lines.stream()
            .filter(str -> {
                int i = Integer.parseInt(str.split(",")[0]);
                return ids.contains(i);
            }).collect(Collectors.toList());

    }

    public static Integer calculateCurrentId(Path pathToDb) {
        List<String> lines;
        try {
            lines = Files.readAllLines(pathToDb, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return lines.size();
    }

    private static String notFoundLine(Integer id) {
        System.out.println("С данным ID: " + id.toString() + " не найдена сущность!");
        return null;
    }

}

package main.java.com.javacore.io_nio.task3.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProjectStatus {

    ACTIVE, FINISHED, DELETED;

    public static List<String> getAllStatus() {
        return Stream.of(ProjectStatus.values())
            .map(ProjectStatus::name)
            .collect(Collectors.toList());
    }
}

package main.java.com.javacore.io_nio.task3.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OperationType {
    READ(1, "Чтение"),
    CREATE(2, "Создание"),
    DELETE(3, "Удаление"),
    UPDATE(4, "Апдейт");

    private final Integer code;
    private final String description;

    OperationType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static List<OperationType> getAllTypes() {
        return Arrays.asList(OperationType.values());
    }

    public static List<String> getAllDescTypes() {
        return Stream.of(OperationType.values())
            .map(OperationType::getDescription)
            .collect(Collectors.toList());
    }

    public static OperationType getByDesc(String description) {
        return Arrays.stream(values())
            .filter(operationType -> operationType.description.equalsIgnoreCase(description))
            .findFirst()
            .orElse(null);
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }
}

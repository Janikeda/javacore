package main.java.com.javacore.io_nio.task3.view;

import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.parseStringForId;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.printSuccessMessage;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.processDeleteRequest;
import static main.java.com.javacore.io_nio.task3.view.OperationType.CREATE;
import static main.java.com.javacore.io_nio.task3.view.OperationType.DELETE;
import static main.java.com.javacore.io_nio.task3.view.OperationType.READ;
import static main.java.com.javacore.io_nio.task3.view.OperationType.UPDATE;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import main.java.com.javacore.io_nio.task3.controller.ProjectController;
import main.java.com.javacore.io_nio.task3.controller.dto.BaseDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;

public class ProjectView extends CommonView {

    private final ProjectController projectController;

    ProjectView() {
        projectController = new ProjectController();
    }

    public static void main(String[] args) {
        new ProjectView().projectViewExecutor();
    }

    /*
     * Операции с консолью: Создание, получение, редактирование и удаление данных
     * Разработчики и счета не могут быть без проекта, следовательно операция создания одинаковая во всех View
     * Операции чтения и удаления без указания ID сущности будут применены ко всем записям.    *
     * */
    private void projectViewExecutor() {
        OperationType operationType;
        boolean isTerminated = false;
        Scanner scanner = new Scanner(System.in);
        while (!isTerminated) {
            System.out.println("Выберите тип операции: " + String
                .join(", ", OperationType.getAllDescTypes()));
            String type = scanner.nextLine();
            operationType = OperationType.getByDesc(type);
            try {
                if (operationType == null) {
                    System.out.println("Данная операция не поддерживается");
                } else if (operationType.equals(READ)) {
                    System.out.println("Введите ID проекта или отображение всех записей");
                    Integer id = parseStringForId(scanner.nextLine());
                    read(id);
                } else if (operationType.equals(CREATE) || operationType.equals(UPDATE)) {
                    ProjectDto projectDto = createProjectDto(scanner, operationType);
                    if (operationType.equals(CREATE)) {
                        printSuccessMessage(create(projectDto));
                    } else {
                        printSuccessMessage(update(projectDto));
                    }
                } else if (operationType.equals(DELETE)) {
                    System.out.println("Введите ID проекта или удалите все записи");
                    Integer id = parseStringForId(scanner.nextLine());
                    delete(id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("Продолжить работу с консолью? Y/N: ");
            String continueChoice = scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("N")) {
                isTerminated = true;
            }
        }
    }


    private void read(Integer id) throws IOException {
        List<? extends BaseDto> dtos = projectController.read(id);
        System.out.println(dtos);
    }

    private boolean create(ProjectDto projectDto) throws IOException {
        boolean result = projectController.create(projectDto);
        read(null);
        return result;
    }

    private void delete(Integer id) throws IOException {
        String result = projectController.delete(id);
        read(null);
        processDeleteRequest(result);
    }

    private boolean update(ProjectDto projectDto) throws IOException {
        boolean result = projectController.update(projectDto);
        read(null);
        return result;
    }

}

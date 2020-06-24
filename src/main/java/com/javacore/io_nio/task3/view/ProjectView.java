package main.java.com.javacore.io_nio.task3.view;

import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.parseStringForId;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.printSuccessMessage;
import static main.java.com.javacore.io_nio.task3.view.OperationType.CREATE;
import static main.java.com.javacore.io_nio.task3.view.OperationType.DELETE;
import static main.java.com.javacore.io_nio.task3.view.OperationType.READ;
import static main.java.com.javacore.io_nio.task3.view.OperationType.UPDATE;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import main.java.com.javacore.io_nio.task3.controller.ProjectController;
import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.DeveloperDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.ProjectStatus;

public class ProjectView {

    private final ProjectController projectController;


    ProjectView() {
        projectController = new ProjectController();
    }

    public static void main(String[] args) {
        new ProjectView().projectViewExecutor();
    }

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
                    printSuccessMessage(delete(id));
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


    private boolean read(Integer id) throws IOException {
        return projectController.read(id);
    }

    private boolean create(ProjectDto projectDto) throws IOException {
        return projectController.save(projectDto);
    }

    private boolean delete(Integer id) throws IOException {
        return projectController.delete(id);
    }

    private boolean update(ProjectDto projectDto) throws IOException {
        return projectController.update(projectDto);
    }

    private ProjectDto createProjectDto(Scanner scanner, OperationType operationType) {
        ProjectDto projectDto = new ProjectDto();
        ProjectStatus projectStatus;
        Set<DeveloperDto> developers = new HashSet<>();
        System.out.println("Выберите статус проекта: " + String
            .join(", ", ProjectStatus.getAllStatus()));
        projectStatus = ProjectStatus.valueOf(scanner.nextLine());
        if (operationType.equals(UPDATE)) {
            System.out.println("Выберите ID проекта для апдейта");
            Integer id = parseStringForId(scanner.nextLine());
            projectDto.setId(id);
        } else {
            System.out.println("Создание разработчиков и их счетов");
            String continueChoice;
            do {
                DeveloperDto developerDto = new DeveloperDto();
                AccountDto accountDto = new AccountDto();
                System.out.println("Выберите DATA для счета:");
                accountDto.setData(scanner.nextLine());
                developerDto.setAccount(accountDto);
                developers.add(developerDto);
                System.out.print("Продолжить добавление разработчиков? Y/N: ");
                continueChoice = scanner.nextLine();
            } while (continueChoice.equalsIgnoreCase("Y"));
        }

        projectDto.setDevelopers(developers);
        projectDto.setProjectStatus(projectStatus);

        return projectDto;
    }

}

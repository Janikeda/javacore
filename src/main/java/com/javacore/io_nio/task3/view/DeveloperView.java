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
import main.java.com.javacore.io_nio.task3.controller.DeveloperController;
import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.BaseDto;
import main.java.com.javacore.io_nio.task3.controller.dto.DeveloperDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;

public class DeveloperView extends CommonView {

    private final DeveloperController developerController;

    public DeveloperView() {
        this.developerController = new DeveloperController();
    }

    public static void main(String[] args) {
        new DeveloperView().developerViewExecutor();
    }

    private void developerViewExecutor() {
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
                    System.out.println("Введите ID разработчика или отображение всех записей");
                    Integer id = parseStringForId(scanner.nextLine());
                    read(id);
                } else if (operationType.equals(CREATE)) {
                    ProjectDto projectDto = createProjectDto(scanner, operationType);
                    printSuccessMessage(create(projectDto));
                } else if (operationType.equals(UPDATE)) {
                    DeveloperDto developerDto = createDeveloperDto(scanner);
                    printSuccessMessage(update(developerDto));
                } else if (operationType.equals(DELETE)) {
                    System.out.println("Введите ID разработчика или удалите все записи");
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
        List<? extends BaseDto> dtos = developerController.read(id);
        System.out.println(dtos);
    }

    private boolean create(ProjectDto projectDto) throws IOException {
        boolean result = developerController.create(projectDto);
        read(null);
        return result;
    }

    private void delete(Integer id) throws IOException {
        String result = developerController.delete(id);
        read(null);
        processDeleteRequest(result);
    }

    private boolean update(DeveloperDto developerDto) throws IOException {
        boolean result = developerController.update(developerDto);
        read(null);
        return result;
    }

    private DeveloperDto createDeveloperDto(Scanner scanner) {
        DeveloperDto developerDto = new DeveloperDto();
        AccountDto accountDto = new AccountDto();
        System.out.println("Выберите ID разработчика для апдейта");
        Integer id = parseStringForId(scanner.nextLine());
        developerDto.setId(id);
        System.out.println("Выберите DATA для счета:");
        accountDto.setData(scanner.nextLine());
        developerDto.setAccount(accountDto);
        return developerDto;
    }
}

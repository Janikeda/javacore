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
import main.java.com.javacore.io_nio.task3.controller.AccountController;
import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.BaseDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;

public class AccountView extends CommonView {

    private final AccountController accountController;

    public AccountView() {
        this.accountController = new AccountController();
    }

    public static void main(String[] args) {
        new AccountView().accountViewExecutor();
    }


    private void accountViewExecutor() {
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
                    System.out.println("Введите ID счета или отображение всех записей");
                    Integer id = parseStringForId(scanner.nextLine());
                    read(id);
                } else if (operationType.equals(CREATE)) {
                    ProjectDto projectDto = createProjectDto(scanner, operationType);
                    printSuccessMessage(create(projectDto));
                } else if (operationType.equals(UPDATE)) {
                    AccountDto accountDto = createAccountDto(scanner);
                    printSuccessMessage(update(accountDto));
                } else if (operationType.equals(DELETE)) {
                    System.out.println("Введите ID счета или удалите все записи");
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
        List<? extends BaseDto> dtos = accountController.read(id);
        System.out.println(dtos);
    }

    private boolean create(ProjectDto projectDto) throws IOException {
        boolean result = accountController.create(projectDto);
        read(null);
        return result;
    }

    private void delete(Integer id) throws IOException {
        String result = accountController.delete(id);
        read(null);
        processDeleteRequest(result);
    }

    private boolean update(AccountDto accountDto) throws IOException {
        boolean result = accountController.update(accountDto);
        read(null);
        return result;
    }

    private AccountDto createAccountDto(Scanner scanner) {
        AccountDto accountDto = new AccountDto();
        System.out.println("Выберите ID счета для апдейта");
        Integer id = parseStringForId(scanner.nextLine());
        accountDto.setId(id);
        System.out.println("Выберите DATA для счета:");
        accountDto.setData(scanner.nextLine());
        return accountDto;
    }

}


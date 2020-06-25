package main.java.com.javacore.io_nio.task3.controller;

import static main.java.com.javacore.io_nio.task3.controller.EntityMapper.fromAccountEntityToDto;
import static main.java.com.javacore.io_nio.task3.utils.ConsoleUtils.printDeleteMessage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import main.java.com.javacore.io_nio.task3.controller.dto.AccountDto;
import main.java.com.javacore.io_nio.task3.controller.dto.BaseDto;
import main.java.com.javacore.io_nio.task3.controller.dto.ProjectDto;
import main.java.com.javacore.io_nio.task3.model.Account;
import main.java.com.javacore.io_nio.task3.model.Developer;

public class AccountController extends CommonController {

    public List<? extends BaseDto> read(Integer id) throws IOException {
        if (id == null) {
            return readAll();
        } else {
            Account entity = accountRepository.findById(id);
            AccountDto accountDto;
            if (entity != null) {
                accountDto = fromAccountEntityToDto(entity);
            } else {
                accountDto = new AccountDto();
            }
            return Collections.singletonList(accountDto);
        }
    }

    public boolean create(ProjectDto projectDto) throws IOException {
        return commonCreate(projectDto);
    }

    public String delete(Integer id) throws IOException {
        if (id == null) {
            deleteAll();
            return "OK";
        } else {
            Account account = accountRepository.findById(id);
            Developer developer = developerRepository.findById(account.getDevId());
            int devs = developerRepository.deleteById(Collections.singletonList(developer.getId()));
            int accs = accountRepository.deleteById(Collections.singletonList(account.getId()));
            return printDeleteMessage(0, devs, accs);
        }
    }

    public boolean update(AccountDto accountDto) throws IOException {
        if (accountDto.getId() == null) {
            return false;
        } else {
            int count = accountRepository.deleteById(Collections.singletonList(accountDto.getId()));
            if (count == 0) {
                return false;
            }
            accountRepository.save(DtoMapper.fromAccDtoToEntity(accountDto));
            return true;
        }
    }
}

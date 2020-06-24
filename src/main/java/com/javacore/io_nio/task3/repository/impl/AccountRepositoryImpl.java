package main.java.com.javacore.io_nio.task3.repository.impl;

import static main.java.com.javacore.io_nio.task3.utils.Constants.DELIMITER;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.calculateCurrentId;
import static main.java.com.javacore.io_nio.task3.utils.DbUtils.findLineById;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import main.java.com.javacore.io_nio.task3.model.Account;
import main.java.com.javacore.io_nio.task3.model.Developer;
import main.java.com.javacore.io_nio.task3.repository.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {

    private final Path pathToDb;
    private Integer currentId;
    private final String DATABASE = "accounts.csv";

    public AccountRepositoryImpl() {
        this.pathToDb = Paths.get(DATABASE);
        this.currentId = calculateCurrentId(this.pathToDb);
    }

    @Override
    public Account findById(Integer id) throws IOException {
        return parseDbRow(findLineById(id, pathToDb));
    }

    @Override
    public List<Account> findAll() throws IOException {
        return findAllCommon(pathToDb, this::parseDbRow);
    }

    @Override
    public Account save(Account entity) throws IOException {
        Integer id;
        if (entity.getId() == null) {
            id = ++currentId;
            entity.setId(id);
        } else {
            id = entity.getId();
        }
        writeToDb(pathToDb, id + DELIMITER + entity.getData());

        return entity;
    }

    @Override
    public Set<Account> saveAll(Set<Developer> developers) throws IOException {
        for (Developer dev : developers) {
            Integer id = ++currentId;
            writeToDb(pathToDb,
                id + DELIMITER + dev.getId() + DELIMITER + dev.getAccount().getData());
            dev.getAccount().setId(id);
        }
        return developers.stream()
            .map(Developer::getAccount)
            .collect(Collectors.toSet());
    }

    @Override
    public List<Account> findByDeveloper(List<Integer> devIds) throws IOException {
        List<String> lines = Files.readAllLines(pathToDb, StandardCharsets.UTF_8);
        List<Account> accounts = lines.stream().map(this::parseDbRow)
            .collect(Collectors.toList());

        return accounts.stream().filter(account -> devIds.contains(account.getDevId()))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() throws IOException {
        deleteAllCommon(pathToDb);
    }

    @Override
    public int deleteById(List<Integer> ids) throws IOException {
        return deleteByIdCommon(ids, pathToDb);
    }

    private Account parseDbRow(String row) {
        if (row == null) {
            return null;
        }
        List<String> columns = Arrays.asList(row.split(DELIMITER));
        Account account = new Account();
        account.setId(Integer.parseInt(columns.get(0)));
        account.setDevId(Integer.parseInt(columns.get(1)));
        account.setData(columns.get(2));
        return account;
    }
}

package main.java.com.javacore.io_nio.task3.repository;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import main.java.com.javacore.io_nio.task3.model.Account;
import main.java.com.javacore.io_nio.task3.model.Developer;

public interface AccountRepository extends GenericRepository<Account, Integer> {

    Set<Account> saveAll(Set<Developer> entities) throws IOException;

    List<Account> findByDeveloper(List<Integer> devIds) throws IOException;
}

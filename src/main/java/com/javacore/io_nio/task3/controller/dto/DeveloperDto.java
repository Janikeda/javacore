package main.java.com.javacore.io_nio.task3.controller.dto;


public class DeveloperDto extends BaseDto {

    private Integer projectId;
    private AccountDto account;

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Developer {" +
            "ID = "+ getId() +
            ", account = " + account +
            "}\n";
    }
}

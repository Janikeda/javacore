package main.java.com.javacore.io_nio.task3.model;

public class Developer extends BaseEntity {

    /*
     * ID проекта
     * */
    private Integer projectId;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}

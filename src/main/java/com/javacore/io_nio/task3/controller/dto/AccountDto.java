package main.java.com.javacore.io_nio.task3.controller.dto;

public class AccountDto extends BaseDto {

    private Integer devId;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    @Override
    public String toString() {
        return "Account {" +
            " ID = "+ getId() +
            ", data ='" + data + '\'' +
            '}';
    }
}

package main.java.com.javacore.io_nio.task3.model;

public class Account extends BaseEntity {

    /*
     * ID разработчика
     * */
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
}

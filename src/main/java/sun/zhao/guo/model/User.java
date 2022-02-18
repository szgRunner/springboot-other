package sun.zhao.guo.model;


import lombok.Data;

@Data
public class User {

    private String id;

    private String userName;

    public User() {
    }

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}

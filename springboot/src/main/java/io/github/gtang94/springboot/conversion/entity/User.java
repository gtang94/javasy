package io.github.gtang94.springboot.conversion.entity;

/**
 * @author tanggq
 * @class User
 * @description
 * @date 2021/6/3
 **/
public class User {
    private String username;
    private Integer age;
    private String created;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", created='" + created + '\'' +
                '}';
    }
}

package io.github.gtang94.springboot.mybatis.bean;

/**
 * @author tanggq
 * @class Role
 * @description
 * @date 2022/4/12
 **/
public class Role {

    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

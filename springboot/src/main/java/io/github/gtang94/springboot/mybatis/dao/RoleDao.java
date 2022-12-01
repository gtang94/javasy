package io.github.gtang94.springboot.mybatis.dao;

import io.github.gtang94.springboot.mybatis.bean.Role;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * @author tanggq
 * @class RoleDao
 * @description
 * @date 2021/5/19
 **/
@Mapper
public interface RoleDao {

    @MapKey("id")
    HashMap<String, String> getHashMapData(String ids);

    void save(@Param("id") Long id, @Param("name") String name);

    Role selectEntity(@Param("id") Long id);
}

package io.github.gtang94.springboot.mybatis.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * @author tanggq
 * @class UserDao
 * @description
 * @date 2022/4/12
 **/
@Mapper
public interface UserDao {

    void save(@Param("id") Long id, @Param("name") String name);
}

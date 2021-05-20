package io.github.gtang94.springboot.mybatis.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

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
}

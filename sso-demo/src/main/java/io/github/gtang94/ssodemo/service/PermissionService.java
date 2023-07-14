package io.github.gtang94.ssodemo.service;


import io.github.gtang94.ssodemo.entity.SysPermission;

import java.util.List;


public interface PermissionService {

    List<SysPermission> findByUserId(Integer userId);

}

package io.github.gtang94.springboot.mybatis.service.impl;

import io.github.gtang94.springboot.mybatis.bean.Role;
import io.github.gtang94.springboot.mybatis.bean.User;
import io.github.gtang94.springboot.mybatis.dao.RoleDao;
import io.github.gtang94.springboot.mybatis.manager.EntityManagerInterface;
import io.github.gtang94.springboot.mybatis.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author tanggq
 * @class UserServiceImpl
 * @description
 * @date 2022/4/12
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private EntityManagerInterface<Role> entityManager;

    @Transactional
    public void save(User user, Role role) {
        int i = 1/0;
        roleDao.save(role.getId(), role.getName());
    }

    @Override
    public Role show() {
        Long id = 1L;
        boolean b = entityManager.hasEntity(id);

        if (b) {
            return (Role) entityManager.getEntity(id, Role.class);
        }

        Role role = roleDao.selectEntity(id);
        entityManager.setEntity(id, role);

        return role;
    }
}

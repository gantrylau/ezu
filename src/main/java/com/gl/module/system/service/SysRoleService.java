package com.gl.module.system.service;

import com.gl.infra.exception.ServiceException;
import com.gl.module.system.domain.SysRole;
import com.gl.module.system.repository.SysRoleRepository;
import com.gl.module.system.vo.SysRoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
@Component
public class SysRoleService {

    @Autowired
    private SysRoleRepository roleRepo;

    public Page<SysRole> listByPage(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }

    public SysRole saveOrUpdate(SysRoleForm form) {
        final SysRole role;
        if (form.getId() != null) {
            role = roleRepo.findOne(form.getId());
            if (role == null)
                throw new ServiceException("用户不存在");
        } else {
            role = new SysRole();
        }
        role.setName(form.getName());
        role.setAlias(form.getAlias());
        role.setPower(form.getPower());
        roleRepo.save(role);
        return role;
    }

    public void delete(Long id) {
        roleRepo.delete(id);
    }
}

package com.gl.module.system.service;

import com.gl.module.system.domain.SysUser;
import com.gl.module.system.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author gantrylau
 * @since 2017/1/27
 */
@Component
public class SysUserService {

    @Autowired
    private SysUserRepository userRepo;

    public Page<SysUser> listByPage(Pageable pageable) {
        return userRepo.findAll(pageable);
    }
}

package com.gl.module.system.service;

import com.gl.module.system.domain.SysMenu;
import com.gl.module.system.repository.SysMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gantrylau
 * @since 2017/1/27
 */
@Component
public class SysMenuService {

    @Autowired
    private SysMenuRepository menuRepo;

    public List<SysMenu> listAllMenus() {
        return menuRepo.findAll();
    }
}

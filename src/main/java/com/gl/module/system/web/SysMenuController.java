package com.gl.module.system.web;

import com.gl.infra.web.GeneralResult;
import com.gl.module.system.domain.SysMenu;
import com.gl.module.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gantrylau
 * @since 2017/1/27
 */
@RestController
@RequestMapping("/sys/menus")
public class SysMenuController {

    @Autowired
    private SysMenuService menuServ;

    @RequestMapping(method = RequestMethod.GET)
    public GeneralResult getMenus() {
        List<SysMenu> menus = menuServ.listAllMenus();
        return GeneralResult.success(menus);
    }



}

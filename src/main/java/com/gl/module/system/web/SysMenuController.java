package com.gl.module.system.web;

import com.gl.infra.web.JsonResult;
import com.gl.module.system.service.SysMenuService;
import com.gl.module.system.vo.SysMenuQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
@RestController
@RequestMapping("/sys/menus")
public class SysMenuController {

    @Autowired
    private SysMenuService menuServ;

    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getMenus(SysMenuQueryParam param) {
        return JsonResult.success(menuServ.listAll(param));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public JsonResult list(SysMenuQueryParam param, Pageable pageable) {
        return JsonResult.success(menuServ.listByPage(param, pageable));
    }
}

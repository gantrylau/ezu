package com.gl.module.system.web;

import com.gl.infra.web.JsonResult;
import com.gl.module.system.domain.SysMenu;
import com.gl.module.system.service.SysMenuService;
import com.gl.module.system.vo.SysMenuQueryParam;
import com.gl.module.system.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult saveMenu(@Valid @RequestBody SysMenuVO vo, BindingResult result) {
        if (result.hasErrors())
            return JsonResult.build(result);

        SysMenu menu = menuServ.save(vo);
        return JsonResult.success(menu.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public JsonResult list(SysMenuQueryParam param, Pageable pageable) {
        return JsonResult.success(menuServ.listByPage(param, pageable));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonResult remove(@PathVariable Long id) {
        menuServ.remove(id);
        return JsonResult.success();
    }
}

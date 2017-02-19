package com.gl.module.system.web;

import com.gl.infra.web.JsonResult;
import com.gl.module.system.service.SysRoleService;
import com.gl.module.system.vo.SysRoleForm;
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
 * @since 2017/2/16
 */
@RestController
@RequestMapping("/sys/roles")
public class SysRoleController {

    @Autowired
    private SysRoleService roleServ;

    @RequestMapping(method = RequestMethod.GET)
    public JsonResult list(Pageable pageable) {
        return JsonResult.success(roleServ.listByPage(pageable));
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult saveOrUpdate(@RequestBody @Valid SysRoleForm form, BindingResult result) {
        if (!result.hasErrors())
            roleServ.saveOrUpdate(form);
        return JsonResult.build(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonResult delete(@PathVariable Long id) {
        roleServ.delete(id);
        return JsonResult.success();
    }
}

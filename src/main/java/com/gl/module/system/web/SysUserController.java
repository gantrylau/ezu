package com.gl.module.system.web;

import com.gl.infra.web.JsonResult;
import com.gl.module.system.service.SysUserService;
import com.gl.module.system.vo.SysUserForm;
import com.gl.module.system.vo.SysUserQueryParam;
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
 * @since 2017/1/27
 */
@RestController
@RequestMapping("/sys/users")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public JsonResult list(SysUserQueryParam param, Pageable pageable) {
        return JsonResult.success(userService.listByPage(param, pageable));
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult save(@Valid @RequestBody SysUserForm form, BindingResult result) {
        if (!result.hasErrors())
            userService.saveOrUpdate(form);
        return JsonResult.build(result);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public JsonResult update(@PathVariable Long id, @Valid @RequestBody SysUserForm form, BindingResult result) {
        if (!result.hasErrors()) {
            form.setId(id);
            userService.saveOrUpdate(form);
        }
        return JsonResult.build(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonResult remove(@PathVariable Long id) {
        userService.remove(id);
        return JsonResult.success();
    }
}

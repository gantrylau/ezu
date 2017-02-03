package com.gl.module.system.web;

import com.gl.infra.web.GeneralResult;
import com.gl.module.system.service.SysUserService;
import com.gl.module.system.vo.SysUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public GeneralResult list(Pageable pageable) {
        return GeneralResult.success(userService.listByPage(pageable));
    }

    @RequestMapping(method = RequestMethod.POST)
    public GeneralResult saveOrUpdate(@Valid SysUserForm form) {
        return GeneralResult.success();
    }
}

package com.gl.module.system.web

import com.gl.infra.web.JsonResult
import com.gl.module.system.service.SysMenuService
import com.gl.module.system.vo.SysMenuQueryParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author gantrylau
 * @since 2017/2/18
 */
@RestController
@RequestMapping("/sys/menus")
class SysMenuController {

    @Autowired
    lateinit var menuServ: SysMenuService

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun getMenus(param: SysMenuQueryParam): JsonResult {
//        val menus = menuServ.listAll()
        return JsonResult.success(menuServ.listAll(param))
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/list")
    fun list(param: SysMenuQueryParam, pageable: Pageable): JsonResult {
        return JsonResult.success(menuServ.listByPage(param, pageable))
    }

}
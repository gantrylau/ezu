package com.gl.module.system.service

import com.gl.module.system.domain.SysMenu
import com.gl.module.system.repository.SysMenuRepository
import com.gl.module.system.vo.SysMenuQueryParam
import com.gl.module.system.vo.SysMenuQueryParam.Type
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import javax.persistence.criteria.Predicate

/**
 * @author gantrylau
 * *
 * @since 2017/1/27
 */
@Component
open class SysMenuService {

    @Autowired
    lateinit var menuRepo: SysMenuRepository

    fun listAll(param: SysMenuQueryParam): List<SysMenu> {
        return menuRepo.findAll({ root, query, cb ->
            val predicates = mutableListOf<Predicate>()
            if (param.id != null)
                predicates.add(cb.notEqual(root.get<Any>("id"), param.id))
            if (!predicates.isEmpty())
                cb.and(* predicates.toTypedArray())
            cb.conjunction()
        })
    }

    fun listByPage(param: SysMenuQueryParam, pageable: Pageable): Page<SysMenu> {
        var alias: String? = null
        if (StringUtils.isNotBlank(param.q)) {
            val condition = SysMenu()
            if (Type.ALIAS == param.type) {
                alias = param.q
            } else {
                condition.name = param.q
                val sysMenu = menuRepo.findOne(Example.of(condition))
                alias = sysMenu.alias
            }
        }
        val _alias = alias
        return menuRepo.findAll({ root, query, cb ->
            val predicates = mutableListOf<Predicate>()
            if (StringUtils.isNotBlank(_alias)) {
                val or = mutableListOf<Predicate>()
                or.add(cb.equal(root.get<Any>("alias"), _alias))
                or.add(cb.equal(root.get<Any>("parentAlias"), _alias))
                predicates.add(cb.or(* or.toTypedArray()))
            }
            if (!predicates.isEmpty())
                cb.and(* predicates.toTypedArray())
            cb.conjunction()
        }, pageable)
    }
}

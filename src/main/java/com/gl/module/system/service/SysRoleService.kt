package com.gl.module.system.service

import com.gl.module.system.domain.SysRole
import com.gl.module.system.repository.SysRoleRepository
import com.gl.module.system.vo.SysRoleForm
import org.hibernate.service.spi.ServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

/**
 * @author gantrylau
 * *
 * @since 2017/2/16
 */
@Component
open class SysRoleService {

    @Autowired
    lateinit var roleRepo: SysRoleRepository

    fun listByPage(pageable: Pageable): Page<SysRole> {
        return roleRepo.findAll(pageable)
    }

    fun saveOrUpdate(form: SysRoleForm): SysRole {
        val role: SysRole?
        if (form.id != null) {
            role = roleRepo.findOne(form.id)
            if (role == null)
                throw ServiceException("用户不存在")
        } else {
            role = SysRole()
        }
        role.name = form.name
        role.alias = form.alias
        role.power = form.power

        roleRepo.save(role)
        return role
    }


    fun delete(id: Long?) {
        roleRepo.delete(id)
    }

}

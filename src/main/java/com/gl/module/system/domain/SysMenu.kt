package com.gl.module.system.domain

import com.gl.infra.spring.data.domain.IdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author gantrylau
 * *
 * @since 2017/1/27
 */
@Entity
@Table(name = "sys_menu")
open class SysMenu : IdEntity<Long>() {

    open var name: String? = null

    open var alias: String? = null

    open var sort: Int? = null

    open var parentAlias: String? = null

    open var isLeaf: Boolean = false
        @Column(name = "is_leaf", nullable = false)
        get
}

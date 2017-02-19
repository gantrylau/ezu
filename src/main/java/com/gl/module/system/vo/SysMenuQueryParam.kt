package com.gl.module.system.vo

/**
 * @author gantrylau
 * *
 * @since 2017/2/17
 */
class SysMenuQueryParam {

    enum class Type {
        /**
         * 父菜单名字
         */
        ALIAS,
        /**
         * 菜单名
         */
        NAME
    }

    var id: Long? = null

    var type: Type? = null

    var q: String? = null
}

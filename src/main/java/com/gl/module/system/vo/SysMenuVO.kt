package com.gl.module.system.vo

import com.gl.module.system.domain.SysMenu
import java.util.*


/**
 * @author gantrylau
 * *
 * @since 2017/2/17
 */
class SysMenuVO(menu: SysMenu) : Cloneable {
    var id: Long? = null
    var name: String? = null
    var alias: String? = null
    var sort: Int? = null
    var parentAlias: String? = null
    var children: MutableList<SysMenuVO>? = null
    var isLeaf: Boolean = false

    init {
        this.id = menu.id
        this.name = menu.name
        this.alias = menu.alias
        this.sort = menu.sort
        this.parentAlias = menu.parentAlias
        this.isLeaf = menu.isLeaf
    }

    fun addChild(list: List<SysMenuVO>) {
        if (this.children == null)
            this.children = mutableListOf()
        this.children?.addAll(list)
    }

    @Throws(CloneNotSupportedException::class)
    override fun clone(): Any {
        return super.clone()
    }

    companion object {

        fun toTree(menus: List<SysMenu>): List<SysMenuVO> {
            val list = mutableListOf<SysMenuVO>()
            menus.mapTo(list) { SysMenuVO(it) }
            list.sortedWith(Comparator { t1, t2 ->
                t1.sort?.compareTo(t2.sort ?: 0) ?: -1
            })
            return formatTree(list, null)
        }

        fun formatTree(list: List<SysMenuVO>, parentAlias: String?): List<SysMenuVO> {
            val result = mutableListOf<SysMenuVO>()
            for (vo in list) {
                if ((parentAlias == null && vo.parentAlias == null) || (parentAlias != null && parentAlias == vo.parentAlias)) {
                    if (!vo.isLeaf)
                        vo.addChild(formatTree(list, vo.alias))
                    result.add(vo)
                }
            }
            return result
        }
    }
}
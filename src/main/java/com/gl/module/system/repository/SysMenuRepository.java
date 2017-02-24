package com.gl.module.system.repository;

import com.gl.infra.spring.data.BaseRepository;
import com.gl.module.system.domain.SysMenu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
public interface SysMenuRepository extends BaseRepository<SysMenu, Long> {

    @Query("select m from SysMenu m where m.name=?1 or m.alias=?2")
    List<SysMenu> findByNameOrAlias(String menu, String alias);
}

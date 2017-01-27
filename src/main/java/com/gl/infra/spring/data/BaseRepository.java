package com.gl.infra.spring.data;

import java.io.Serializable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author gantrylau
 * @since 2016年03月31日
 */
@NoRepositoryBean
public interface BaseRepository<T extends Persistable<ID>, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}

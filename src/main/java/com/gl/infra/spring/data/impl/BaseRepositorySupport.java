package com.gl.infra.spring.data.impl;

import com.gl.infra.spring.data.BaseRepository;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author gantrylau
 * @since 2016年03月31日
 */
public class BaseRepositorySupport<T extends Persistable<ID>, ID extends Serializable> extends
        SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    public BaseRepositorySupport(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseRepositorySupport(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}

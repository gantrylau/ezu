package com.gl.infra.spring.data;

import com.gl.infra.spring.data.impl.BaseRepositorySupport;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author gantrylau
 * @since 2016年03月31日
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends Persistable<I>, I extends Serializable> extends
        JpaRepositoryFactoryBean<R, T, I> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory<T,I>(entityManager);
    }

    private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            JpaEntityInformation<T, I> jpaEntityInfo=(JpaEntityInformation<T, I>)this.getEntityInformation(metadata.getDomainType());
            return new BaseRepositorySupport(jpaEntityInfo, entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositorySupport.class;
        }
    }
}

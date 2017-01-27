package com.gl.infra.spring.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.springframework.data.domain.Persistable;

/**
 * @author gantrylau
 * @since 2016年03月31日
 */
@MappedSuperclass
public abstract class IdEntity<ID extends Serializable> implements Serializable, Persistable<ID> {

    private static final long serialVersionUID = 1805525954744391944L;
    protected ID id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}

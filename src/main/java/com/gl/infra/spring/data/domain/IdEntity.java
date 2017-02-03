package com.gl.infra.spring.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

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

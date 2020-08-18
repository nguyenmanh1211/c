package com.sapo.jwt.model.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long i) {
        this.id = i;
    }

    public Long getId() {
        return id;
    }
}

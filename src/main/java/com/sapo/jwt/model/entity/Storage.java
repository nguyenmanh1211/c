package com.sapo.jwt.model.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "storages")
@Getter
@Setter
@ToString
public class Storage {
    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "storage_code", unique = true)
    private String storageCode;
    @Column(name = "storage_name")
    private String storageName;
    @Column(name = "storage_address")
    private String storageAddress;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "status")
    private Boolean status;
}

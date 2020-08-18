package com.sapo.jwt.repository;

import com.sapo.jwt.model.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Long> {

    @Query("select s from Storage s where s.storageName like %:name%")
    List<Storage> findAllCustom(String name);

    @Query("select s from Storage s where s.id = :id")
    Storage findOneCustom(long id);

    @Query(value = "select SUBSTRING_INDEX(storage_code,'o',-1) as st_code_max from storages where storage_code like '%kho%' order by st_code_max desc limit 0,1", nativeQuery = true)
    Long getCodeMax();
}

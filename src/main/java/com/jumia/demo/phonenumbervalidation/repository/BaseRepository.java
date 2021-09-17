package com.jumia.demo.phonenumbervalidation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.jumia.demo.phonenumbervalidation.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity<ID>,ID extends Serializable> extends JpaRepository<Entity, ID> {

}

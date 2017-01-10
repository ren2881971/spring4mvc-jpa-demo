package com.jit.ota4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/8.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, S, ID>{
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager e) {
        return new BaseRepositoryFactory(e);
    }
}

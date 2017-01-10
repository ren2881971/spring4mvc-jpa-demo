package com.jit.ota4.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/8.
 */
public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID>{
    @PersistenceContext
    private final EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    public int executeUpdateBySQL(String sql, List<Object> param) {
        Query query = em.createNativeQuery(sql);
        if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
            }
            return query.executeUpdate();
        }
        return 0;
    }

    @Override
    public int executeUpdateBySQL(String sql, Map<String, Object> param) {
        Query query = em.createNativeQuery(sql);
        if(param != null && !param.isEmpty()){
            for(String name:param.keySet()){
                query.setParameter(name,param.get(name));
            }
            return query.executeUpdate();
        }
        return 0;
    }

    @Override
    public int executeUpdateByHql(String hql, List<Object> param) {
        Query query = em.createQuery(hql);
        if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
            }
            return query.executeUpdate();
        }
        return 0;
    }

    @Override
    public int executeUpdateByHql(String hql, Map<String, Object> param) {
        Query query = em.createQuery(hql);
        if(param != null && !param.isEmpty()){
            for(String name:param.keySet()){
                query.setParameter(name,param.get(name));
            }
            return query.executeUpdate();
        }
        return 0;
    }

    @Override
    public List<T> findByHql(String hql, List<Object> param,Class<T> t) {
       Query query = em.createQuery(hql,t);
       if(param != null && !param.isEmpty()){
           for(int i = 0 ;i<param.size();i++){
               query.setParameter(i+1,param.get(i));
           }
           return query.getResultList();
       }
       return null;
    }

    @Override
    public List<T> findByHqlWithPage(String hql, List<Object> param,Class<T> t,int pageNo, int pageSize) {
        Query query = em.createQuery(hql,t);
        if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
                query.setFirstResult((pageNo-1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> findByHql(String hql, List<Object> param) {
        Query query = em.createQuery(hql);
        if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
            }
            return query.getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> findByHqlWithPage(String hql, List<Object> param, int pageNo, int pageSize) {
        Query query = em.createQuery(hql);
        if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
                query.setFirstResult((pageNo-1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> findBySQL(String sql, List<Object> param) {
       Query query = em.createNativeQuery(sql);
       if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
            }
            return query.getResultList();
       }
       return null;
    }

    @Override
    public List<Object[]> findBySQLWithPage(String sql, List<Object> param, int pageNo, int pageSize) {
        Query query = em.createNativeQuery(sql);
        if(param != null && !param.isEmpty()){
            for(int i = 0 ;i<param.size();i++){
                query.setParameter(i+1,param.get(i));
                query.setFirstResult((pageNo-1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        }
        return null;
    }
}

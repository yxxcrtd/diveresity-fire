package com.hublotcloud.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hublotcloud.service.BaseService;

/**
 * Generic Service Implement
 *
 * @param <T>
 * @param <PK>
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JpaRepository<T, PK> baseRepository;

    @Override
    public T getById(PK id) {
        return baseRepository.getReferenceById(id);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

}

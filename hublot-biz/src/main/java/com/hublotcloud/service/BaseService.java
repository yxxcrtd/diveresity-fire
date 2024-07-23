package com.hublotcloud.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;

/**
 * Generic Service
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable> {

    T getById(PK id);

    List<T> findAll(Sort sort);

}

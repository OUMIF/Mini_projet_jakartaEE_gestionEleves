package com.tp2.tp2.dao;

import java.util.List;

public interface ICRUD <T,ID> {
    List<T> getAll();
    T findById(ID id);
    boolean create(T object);
    boolean update(T object);
    boolean delete(ID id);
}

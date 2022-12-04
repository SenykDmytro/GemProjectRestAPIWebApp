package com.senyk.gemProject.services.interfaces;

import java.util.List;

public interface Service<T> {
    public List<T> getList();
    public void addNew(T t);
    public void removeObj(Long id);
}

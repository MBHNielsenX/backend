package com.example.demo.repositories;

import java.util.List;

public interface CRUDInterface <T>{
    //Create
    public void create(T entity);

    //Read
    public T getSingleEntityById(int iD);
    public List<T> getAllEntities();

    //Update
    public boolean update(T entity);

    //Delete
    public boolean deleteById(int id);
}
